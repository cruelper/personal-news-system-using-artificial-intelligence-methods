package ru.nuykin.diplom.service;

import org.telegram.telegrambots.meta.api.objects.Update;

public interface ConsumerService {
    void consumeNewsFeedback(Update update);

    void consumeNewsPrediction(Update update);

    void consumeNews();
}
