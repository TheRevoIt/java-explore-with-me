package ru.practicum.stat.model.dao;

import ru.practicum.statdto.dto.ViewStatsDto;

import java.time.LocalDateTime;
import java.util.List;

public interface ViewStatDao {
    List<ViewStatsDto> getStats(LocalDateTime start, LocalDateTime end, boolean unique, List<String> urls);
}
