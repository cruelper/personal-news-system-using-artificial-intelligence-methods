package ru.nuykin.diplom.service;

import org.telegram.telegrambots.meta.api.objects.Update;
import ru.nuykin.diplom.model.MyCallbackQuery;

public interface FeedbackService {
    void feedback(MyCallbackQuery myCallbackQuery);
}

