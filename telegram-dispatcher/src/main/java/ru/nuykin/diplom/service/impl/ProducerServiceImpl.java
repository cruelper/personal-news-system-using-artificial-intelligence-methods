package ru.nuykin.diplom.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.nuykin.diplom.service.ProducerService;

@Service
@Log4j2
@RequiredArgsConstructor
public class ProducerServiceImpl implements ProducerService {
    private final KafkaTemplate<String, Update> kafkaTemplate;

    @Override
    public void produce(String kafkaTopic, Update update) {
        log.debug(update.getMessage().getText());
        kafkaTemplate.send(kafkaTopic, update);
    }
}
