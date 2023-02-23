package ru.nuykin.diplom.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.nuykin.diplom.controller.UpdateProcessor;
import ru.nuykin.diplom.model.KafkaTopic;
import ru.nuykin.diplom.service.AnswerConsumer;

@Service
@RequiredArgsConstructor
public class AnswerConsumerImpl implements AnswerConsumer {
    private final UpdateProcessor updateProcessor;

    @Override
    @KafkaListener(topics = KafkaTopic.RESPONSE_TOPIC)
    public void consume(SendMessage sendMessage) {
        updateProcessor.setView(sendMessage);
    }
}
