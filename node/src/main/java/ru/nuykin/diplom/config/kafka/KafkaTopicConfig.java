package ru.nuykin.diplom.config.kafka;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaTopicConfig {

    @Value(value = "${spring.kafka.bootstrap-servers}")
    private String bootstrapAddress;

    @Bean
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        return new KafkaAdmin(configs);
    }

    @Bean
    public NewTopic predictRequestTopic() {
        return TopicBuilder.name(KafkaTopic.PREDICT_REQUEST_TOPIC)
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic predictResponseTopic() {
        return TopicBuilder.name(KafkaTopic.PREDICT_RESPONSE_TOPIC)
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic feedbackResponseTopic() {
        return TopicBuilder.name(KafkaTopic.FEEDBACK_RESPONSE_TOPIC)
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic feedbackRequestTopic() {
        return TopicBuilder.name(KafkaTopic.FEEDBACK_REQUEST_TOPIC)
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic newsTopic() {
        return TopicBuilder.name(KafkaTopic.NEWS_TOPIC)
                .partitions(1)
                .replicas(1)
                .build();
    }
}
