package ru.nuykin.diplom.service;

import org.telegram.telegrambots.meta.api.objects.Update;
import ru.nuykin.diplom.model.MyCallbackQuery;
import ru.nuykin.diplom.model.MyUpdateQuery;

public interface ConsumerService {
    void consumeNewsFeedback(MyCallbackQuery myCallbackQuery);

    void consumeNewsPrediction(MyUpdateQuery update);

    void consumeSimilarNews(MyCallbackQuery myCallbackQuery);

    void consumeNews();
}
