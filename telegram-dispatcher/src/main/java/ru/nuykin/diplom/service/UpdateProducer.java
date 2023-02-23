package ru.nuykin.diplom.service;

import org.telegram.telegrambots.meta.api.objects.Update;

public interface UpdateProducer {
    void produce(String kafkaTopic, Update update);
}
