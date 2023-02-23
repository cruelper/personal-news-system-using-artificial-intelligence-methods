package ru.nuykin.diplom.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.nuykin.diplom.service.UpdateProducer;

@Service
@Log4j
@RequiredArgsConstructor
public class UpdateProducerImpl implements UpdateProducer {
    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Override
    public void produce(String kafkaTopic, Update update) {
        log.debug(update.getMessage().getText());
        kafkaTemplate.send(kafkaTopic, update);
    }
}
