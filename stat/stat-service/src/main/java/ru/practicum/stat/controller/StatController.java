package ru.practicum.stat.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.stat.service.StatService;
import ru.practicum.statdto.dto.HitDto;
import ru.practicum.statdto.dto.ViewStatsDto;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
public class StatController {
    private final StatService statService;

    @PostMapping("/hit")
    HitDto save(@RequestBody HitDto hitDto) {
        log.info("Perform saving");
        return statService.save(hitDto);
    }

    @GetMapping("/stats")
    List<ViewStatsDto> getStats(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime start,
                                @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime end,
                                @RequestParam(defaultValue = "false") boolean unique,
                                @RequestParam(name = "uris", required = false) List<String> urls) {
        log.info("Get stats");
        return statService.getStats(start, end, unique, urls);
    }
}
