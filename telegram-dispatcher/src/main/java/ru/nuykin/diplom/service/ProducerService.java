package ru.nuykin.diplom.service;

import org.telegram.telegrambots.meta.api.objects.Update;
import ru.nuykin.diplom.model.MyCallbackQuery;
import ru.nuykin.diplom.model.MyUpdateQuery;

public interface ProducerService {
    void produce(String kafkaTopic, MyUpdateQuery update);

    void produce(String kafkaTopic, MyCallbackQuery myCallbackQuery);
}
