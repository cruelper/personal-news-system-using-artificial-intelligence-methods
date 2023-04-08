package ru.nuykin.diplom.service;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public interface ProducerService {
    void produce(String kafkaTopic, SendMessage sendMessage);
}

