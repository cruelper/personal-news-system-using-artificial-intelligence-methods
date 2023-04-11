package ru.nuykin.diplom.service;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public interface AnswerConsumer {
    void consumeNewsFeedback(SendMessage sendMessage);

    void consumeNewsPrediction(SendMessage sendMessage);

    void consumeSimilarNews(SendMessage sendMessage);
}
