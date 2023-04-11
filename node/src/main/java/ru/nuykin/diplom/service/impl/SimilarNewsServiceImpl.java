package ru.nuykin.diplom.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.nuykin.diplom.config.kafka.KafkaTopic;
import ru.nuykin.diplom.model.MyCallbackQuery;
import ru.nuykin.diplom.service.SimilarNewsService;

@Service
@RequiredArgsConstructor
public class SimilarNewsServiceImpl implements SimilarNewsService {
    private final ProducerServiceImpl producerService;
    @Override
    public void find(MyCallbackQuery myCallbackQuery) {
        SendMessage sendMessage = new SendMessage(
                myCallbackQuery.getChatId().toString(),
                "Ответ на сообщение с показом похожих новостей"
        );
        producerService.produce(KafkaTopic.SIMILAR_RESPONSE_TOPIC, sendMessage);
    }
}
