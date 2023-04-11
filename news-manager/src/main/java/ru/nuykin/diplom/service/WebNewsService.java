package ru.nuykin.diplom.service;

import ru.nuykin.diplom.dto.NewsSourceDto;
import ru.nuykin.diplom.exception.NewsSourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nuykin.diplom.dto.NewsDto;
import ru.nuykin.diplom.model.News;
import ru.nuykin.diplom.model.NewsSource;
import ru.nuykin.diplom.repository.NewsRepository;
import ru.nuykin.diplom.repository.NewsSourceRepository;
import ru.nuykin.diplom.util.Request;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WebNewsService {
    private final NewsRepository newsRepository;
    private final NewsSourceRepository newsSourceRepository;
    private final Request request;

    public Long createNews(NewsDto newsDto) {
        Optional<NewsSource> newsSource = newsSourceRepository.findById(
                newsDto.getIdNewsSource()
        );
        if (newsSource.isEmpty()) throw new NewsSourceNotFoundException();

        News news = new News(
                newsDto.getTitle(),
                newsDto.getUrl(),
                request.getEmbeddingRequest(newsDto.getTitle()),
                newsDto.getDate(),
                newsSource.get()
        );

        return newsRepository.save(news).getId();
    }

    public Long createNewsSource(NewsSourceDto newsSourceDto) {
        NewsSource newsSource = new NewsSource(
                newsSourceDto.getName(),
                newsSourceDto.getUrl()
        );

        return newsSourceRepository.save(newsSource).getId();
    }
}
