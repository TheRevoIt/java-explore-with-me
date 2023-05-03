package ru.practicum.stat.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.stat.model.HitMapper;
import ru.practicum.stat.model.StatsMapper;
import ru.practicum.stat.repository.StatRepository;
import ru.practicum.statdto.dto.HitDto;
import ru.practicum.statdto.dto.ViewStatsDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StatServiceImpl implements StatService {
    private final StatRepository statRepository;

    @Override
    public HitDto save(HitDto hitDto) {
        statRepository.save(HitMapper.toHit(hitDto));
        return hitDto;
    }

    @Override
    public List<ViewStatsDto> getStats(LocalDateTime start, LocalDateTime end, boolean unique, List<String> urls) {
        if (urls == null || urls.isEmpty()) {
            if (unique) {
                return statRepository.getAllStatsUnique(start, end, urls).stream().map(StatsMapper::toViewStatsDto)
                        .collect(Collectors.toList());
            } else {
                return statRepository.getAllStats(start, end, urls).stream().map(StatsMapper::toViewStatsDto)
                        .collect(Collectors.toList());
            }
        } else {
            if (unique) {
                return statRepository.getAllStatsUniqueNoUrl(start, end).stream().map(StatsMapper::toViewStatsDto)
                        .collect(Collectors.toList());
            } else {
                return statRepository.getAllStatsNoUrl(start, end).stream().map(StatsMapper::toViewStatsDto)
                        .collect(Collectors.toList());
            }
        }
    }
}
