package ru.practicum.stat.model.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.practicum.stat.model.Hit;
import ru.practicum.stat.model.Stats;
import ru.practicum.stat.model.StatsMapper;
import ru.practicum.statdto.dto.ViewStatsDto;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class ViewStatDaoImpl implements ViewStatDao {
    private final EntityManager entityManager;

    @Override
    public List<ViewStatsDto> getStats(LocalDateTime start, LocalDateTime end, boolean unique, List<String> urls) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Stats> viewStatsCriteriaQuery = criteriaBuilder.createQuery(Stats.class);
        Root<Hit> hitResult = viewStatsCriteriaQuery.from(Hit.class);
        List<Predicate> predicates = new ArrayList<>();

        if (urls != null) {
            predicates.add(criteriaBuilder.or(urls.stream()
                    .map(uri -> criteriaBuilder.like(hitResult.get("uri"), uri))
                    .collect(Collectors.toList())
                    .toArray(Predicate[]::new)
            ));
        }
        viewStatsCriteriaQuery.select(criteriaBuilder.construct(Stats.class,
                        hitResult.get("uri"),
                        hitResult.get("app"),
                        unique ? criteriaBuilder.countDistinct(hitResult.get("ip")) : criteriaBuilder.count(hitResult.get("ip"))
                )
        );
        viewStatsCriteriaQuery.groupBy(
                hitResult.get("uri"),
                hitResult.get("app"),
                hitResult.get("ip")
        );
        viewStatsCriteriaQuery.orderBy(criteriaBuilder
                .desc(unique ?
                        criteriaBuilder.countDistinct(hitResult.get("ip"))
                        : criteriaBuilder.count(hitResult.get("ip"))));
        Predicate betweenPredicate = criteriaBuilder
                .between(hitResult.get("timestamp"), start, end);
        predicates.add(betweenPredicate);
        viewStatsCriteriaQuery.where(predicates.toArray(Predicate[]::new));

        return entityManager.createQuery(viewStatsCriteriaQuery)
                .getResultList()
                .stream()
                .map(StatsMapper::toViewStatsDto)
                .collect(Collectors.toList());
    }
}