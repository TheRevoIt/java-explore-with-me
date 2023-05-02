package ru.practicum.stat.model;

import ru.practicum.statdto.dto.HitDto;

public class HitMapper {
    public static Hit toHit(HitDto hitDto) {
        return Hit.builder()
                .id(hitDto.getId())
                .uri(hitDto.getUri())
                .ip(hitDto.getIp())
                .app(hitDto.getApp())
                .timestamp(hitDto.getTimestamp())
                .build();
    }

    public static HitDto toHitDto(Hit hit) {
        return HitDto.builder()
                .id(hit.getId())
                .uri(hit.getUri())
                .ip(hit.getIp())
                .app(hit.getApp())
                .timestamp(hit.getTimestamp())
                .build();
    }
}
