package ru.nuykin.diplom.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.nuykin.diplom.config.kafka.KafkaTopic;
import ru.nuykin.diplom.model.MyCallbackQuery;
import ru.nuykin.diplom.service.FeedbackService;

@Service
@RequiredArgsConstructor
public class FeedbackServiceImpl implements FeedbackService {
    private final ProducerServiceImpl producerService;
    @Override
    public void feedback(MyCallbackQuery myCallbackQuery) {
        SendMessage sendMessage = new SendMessage(
                myCallbackQuery.getChatId().toString(),
                "Ответ на сообщение с оценкой подборки новостей"
        );
        producerService.produce(KafkaTopic.FEEDBACK_RESPONSE_TOPIC, sendMessage);
    }
}