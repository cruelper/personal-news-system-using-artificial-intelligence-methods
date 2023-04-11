package ru.nuykin.diplom.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.nuykin.diplom.config.kafka.KafkaTopic;
import ru.nuykin.diplom.model.MyCallbackQuery;
import ru.nuykin.diplom.model.MyUpdateQuery;
import ru.nuykin.diplom.service.ConsumerService;

@Service
@RequiredArgsConstructor
public class ConsumerServiceImpl implements ConsumerService {
    private final FeedbackServiceImpl feedbackService;

    private final PredictServiceImpl predictService;

    private final SimilarNewsServiceImpl similarNewsService;

    @Override
    @KafkaListener(topics = KafkaTopic.PREDICT_REQUEST_TOPIC, groupId = "mainGroup")
    public void consumeNewsPrediction(MyUpdateQuery update) {
        predictService.predict(update);
    }

    @Override
    @KafkaListener(topics = KafkaTopic.FEEDBACK_REQUEST_TOPIC, groupId = "mainGroup")
    public void consumeNewsFeedback(MyCallbackQuery myCallbackQuery) {
        feedbackService.feedback(myCallbackQuery);
    }

    @Override
    @KafkaListener(topics = KafkaTopic.SIMILAR_REQUEST_TOPIC, groupId = "mainGroup")
    public void consumeSimilarNews(MyCallbackQuery myCallbackQuery) {
        similarNewsService.find(myCallbackQuery);
    }

    @Override
    @KafkaListener(topics = KafkaTopic.NEWS_TOPIC, groupId = "mainGroup")
    public void consumeNews() {

    }
}
