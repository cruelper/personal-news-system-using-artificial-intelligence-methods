package ru.nuykin.diplom.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.nuykin.diplom.config.kafka.KafkaTopic;
import ru.nuykin.diplom.model.MyUpdateQuery;
import ru.nuykin.diplom.service.NewsService;
import ru.nuykin.diplom.service.PredictService;
import ru.nuykin.diplom.util.MessageUtil;

@Service
@RequiredArgsConstructor
public class PredictServiceImpl implements PredictService {
    private final ProducerServiceImpl producerService;
    private final NewsService newsService;

    @Override
    public void predict(MyUpdateQuery update) {
        SendMessage sendMessage = new SendMessage(
                update.getMessage().getChatId().toString(),
                "Ответ на сообщение с оценкой подборки новостей"
        );

        sendMessage.setReplyMarkup(MessageUtil.generateRatingButtonsAndSimilarButtonMarkup(
                update.getMessage().getChatId(),
                update.getMessage().getFrom().getId(),
                //id news
                1L,
                //message id не нужен. он получается во время колбека
                update.getKeycloakEmail()
                ));
        producerService.produce(KafkaTopic.PREDICT_RESPONSE_TOPIC, sendMessage);
    }
}

