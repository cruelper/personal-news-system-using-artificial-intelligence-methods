package ru.nuykin.diplom.config.kafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import ru.nuykin.diplom.model.MyCallbackQuery;
import ru.nuykin.diplom.model.MyUpdateQuery;

import java.util.HashMap;
import java.util.Map;

@Configuration
class KafkaConsumerConfig {
    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Bean
    public Map<String, Object> SendMessageObjConsumerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        return props;
    }

    @Bean
    public ConsumerFactory<String, MyUpdateQuery> SendMessageObjConsumerFactory() {
        return new DefaultKafkaConsumerFactory<>(
                SendMessageObjConsumerConfigs(),
                new StringDeserializer(),
                new JsonDeserializer<MyUpdateQuery>().trustedPackages("*")
        );
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, MyUpdateQuery> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, MyUpdateQuery> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(SendMessageObjConsumerFactory());
        return factory;
    }

    @Bean
    public ConsumerFactory<String, MyCallbackQuery> MyCallbackQueryObjConsumerFactory() {
        return new DefaultKafkaConsumerFactory<>(
                SendMessageObjConsumerConfigs(),
                new StringDeserializer(),
                new JsonDeserializer<MyCallbackQuery>().trustedPackages("*")
        );
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, MyCallbackQuery> myCallbackQuerykafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, MyCallbackQuery> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(MyCallbackQueryObjConsumerFactory());
        return factory;
    }
}
