package ru.nuykin.diplom.service;

import org.telegram.telegrambots.meta.api.objects.Update;
import ru.nuykin.diplom.model.MyCallbackQuery;

public interface DistributorService {
    void distribute(Update update);
    void distribute(MyCallbackQuery myCallbackQuery);
}

