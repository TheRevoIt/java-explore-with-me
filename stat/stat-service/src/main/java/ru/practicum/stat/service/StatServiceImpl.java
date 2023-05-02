package ru.practicum.stat.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.stat.model.HitMapper;
import ru.practicum.stat.repository.StatRepository;
import ru.practicum.statdto.dto.HitDto;
import ru.practicum.statdto.dto.ViewStatsDto;

import java.time.LocalDateTime;
import java.util.List;

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
        return statRepository.getStats(start, end, unique, urls);
    }
}
