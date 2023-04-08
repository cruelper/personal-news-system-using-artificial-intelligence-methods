package ru.nuykin.diplom.service;

import org.telegram.telegrambots.meta.api.objects.Update;

public interface ProducerService {
    void produce(String kafkaTopic, Update update);
}
