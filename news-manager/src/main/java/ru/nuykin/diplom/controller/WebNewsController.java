package ru.nuykin.diplom.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nuykin.diplom.dto.NewsDto;
import ru.nuykin.diplom.dto.NewsSourceDto;
import ru.nuykin.diplom.service.WebNewsService;

@RestController
@RequestMapping("/news")
@RequiredArgsConstructor
public class WebNewsController {
    private final WebNewsService webNewsService;

    @PostMapping(path = "/")
    public ResponseEntity<Long> createNews(
            @RequestBody NewsDto newsDto
    ) {
        return ResponseEntity.ok(
                webNewsService.createNews(newsDto)
        );
    }

    @PostMapping(path = "/news-source/")
    public ResponseEntity<Long> createNewsSource(
            @RequestBody NewsSourceDto newsSourceDto
    ) {
        return ResponseEntity.ok(
                webNewsService.createNewsSource(newsSourceDto)
        );
    }
}
