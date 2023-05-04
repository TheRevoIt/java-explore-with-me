package ru.practicum.stat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.practicum.stat.model.Hit;
import ru.practicum.stat.model.Stats;

import java.time.LocalDateTime;
import java.util.List;

public interface StatRepository extends JpaRepository<Hit, Long> {
    @Query("SELECT new ru.practicum.stat.model.Stats(hits.app, hits.uri, COUNT(DISTINCT hits.ip)) " +
            "FROM Hit AS hits " +
            "WHERE hits.timestamp BETWEEN ?1 AND ?2 " +
            "AND hits.uri IN (?3) " +
            "GROUP BY hits.app, hits.uri " +
            "ORDER BY COUNT(DISTINCT hits.ip) DESC")
    List<Stats> getAllStatsUnique(LocalDateTime start, LocalDateTime end, List<String> uri);

    @Query("SELECT new ru.practicum.stat.model.Stats(hits.app, hits.uri, COUNT(hits.ip)) " +
            "FROM Hit AS hits " +
            "WHERE hits.timestamp BETWEEN ?1 AND ?2 " +
            "AND hits.uri IN (?3) " +
            "GROUP BY hits.app, hits.uri " +
            "ORDER BY COUNT(hits.ip) DESC")
    List<Stats> getAllStats(LocalDateTime start, LocalDateTime end, List<String> uri);

    @Query("SELECT new ru.practicum.stat.model.Stats(hits.app, hits.uri, COUNT(DISTINCT hits.ip)) " +
            "FROM Hit AS hits " +
            "WHERE hits.timestamp BETWEEN ?1 AND ?2 " +
            "GROUP BY hits.app, hits.uri " +
            "ORDER BY COUNT(DISTINCT hits.ip) DESC")
    List<Stats> getAllStatsUniqueNoUrl(LocalDateTime start, LocalDateTime end);

    @Query("SELECT new ru.practicum.stat.model.Stats(hits.app, hits.uri, COUNT(hits.ip)) " +
            "FROM Hit AS hits " +
            "WHERE hits.timestamp BETWEEN ?1 AND ?2 " +
            "GROUP BY hits.app, hits.uri " +
            "ORDER BY COUNT(hits.ip) DESC")
    List<Stats> getAllStatsNoUrl(LocalDateTime start, LocalDateTime end);
}
