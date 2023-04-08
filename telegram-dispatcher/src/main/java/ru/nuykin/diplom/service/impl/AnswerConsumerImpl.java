package ru.nuykin.diplom.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.nuykin.diplom.config.kafka.KafkaTopic;
import ru.nuykin.diplom.controller.UpdateProcessor;
import ru.nuykin.diplom.service.AnswerConsumer;

@Service
@RequiredArgsConstructor
public class AnswerConsumerImpl implements AnswerConsumer {
    private final UpdateProcessor updateProcessor;

    @Override
    @KafkaListener(topics = KafkaTopic.FEEDBACK_RESPONSE_TOPIC, groupId = "mainGroup")
    public void consumeNewsFeedback(SendMessage sendMessage) {
        updateProcessor.setView(sendMessage);
    }

    @Override
    @KafkaListener(topics = KafkaTopic.PREDICT_RESPONSE_TOPIC, groupId = "mainGroup")
    public void consumeNewsPrediction(SendMessage sendMessage) {
        updateProcessor.setView(sendMessage);
    }
}
