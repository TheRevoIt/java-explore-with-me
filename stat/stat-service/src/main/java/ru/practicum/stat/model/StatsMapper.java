package ru.practicum.stat.model;

import ru.practicum.statdto.dto.ViewStatsDto;

public class StatsMapper {
    public static Stats toStats(ViewStatsDto viewStatsDto) {
        return Stats.builder()
                .uri(viewStatsDto.getUri())
                .app(viewStatsDto.getApp())
                .hits(viewStatsDto.getHits())
                .build();
    }

    public static ViewStatsDto toViewStatsDto(Stats stats) {
        return ViewStatsDto.builder()
                .uri(stats.getUri())
                .app(stats.getApp())
                .hits(stats.getHits())
                .build();
    }
}