package ru.nuykin.diplom.config.kafka;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;
import ru.nuykin.diplom.model.MyCallbackQuery;
import ru.nuykin.diplom.model.MyUpdateQuery;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducerConfig {

    @Value(value = "${spring.kafka.bootstrap-servers}")
    private String bootstrapAddress;

    @Bean
    public Map<String, Object> updateObjProducerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return props;
    }

    @Bean
    public ProducerFactory<String, MyUpdateQuery> updateObjProducerFactory() {
        return new DefaultKafkaProducerFactory<>(updateObjProducerConfigs());
    }

    @Bean
    public KafkaTemplate<String, MyUpdateQuery> updateObjKafkaTemplate() {
        return new KafkaTemplate<>(updateObjProducerFactory());
    }

    @Bean
    public ProducerFactory<String, MyCallbackQuery> myCallbackQueryObjProducerFactory() {
        return new DefaultKafkaProducerFactory<>(updateObjProducerConfigs());
    }

    @Bean
    public KafkaTemplate<String, MyCallbackQuery> myCallbackQueryObjKafkaTemplate() {
        return new KafkaTemplate<>(myCallbackQueryObjProducerFactory());
    }
}
