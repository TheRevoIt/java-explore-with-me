package ru.practicum.stat.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.stat.dto.HitDto;
import ru.practicum.stat.dto.ViewStatsDto;
import ru.practicum.stat.service.StatService;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
public class StatController {
    private final StatService statService;

    @PostMapping("/hit")
    HitDto save(@RequestBody HitDto hitDto) {
        return statService.save(hitDto);
    }

    @GetMapping("/stats")
    ViewStatsDto getStats(@RequestParam LocalDateTime start,
                          @RequestParam LocalDateTime end,
                          @RequestParam(defaultValue = "false") boolean unique,
                          @RequestParam(required = false) List<String> urls) {
        return statService.getStats(start, end, unique, urls);
    }
}
