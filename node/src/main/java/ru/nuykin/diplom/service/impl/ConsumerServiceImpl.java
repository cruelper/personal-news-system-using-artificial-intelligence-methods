package ru.nuykin.diplom.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.nuykin.diplom.config.kafka.KafkaTopic;
import ru.nuykin.diplom.service.ConsumerService;

@Service
@RequiredArgsConstructor
public class ConsumerServiceImpl implements ConsumerService {
    private final FeedbackServiceImpl feedbackService;

    private final PredictServiceImpl predictService;

    @Override
    @KafkaListener(topics = KafkaTopic.FEEDBACK_REQUEST_TOPIC, groupId = "mainGroup")
    public void consumeNewsFeedback(Update update) {
    }

    @Override
    @KafkaListener(topics = KafkaTopic.PREDICT_REQUEST_TOPIC, groupId = "mainGroup")
    public void consumeNewsPrediction(Update update) {
    }

    @Override
    @KafkaListener(topics = KafkaTopic.NEWS_TOPIC, groupId = "mainGroup")
    public void consumeNews() {

    }
}
