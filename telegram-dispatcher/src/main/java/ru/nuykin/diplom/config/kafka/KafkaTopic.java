package ru.nuykin.diplom.config.kafka;

public class KafkaTopic {
    // telegram dispatcher to news feedback
    public static final String FEEDBACK_REQUEST_TOPIC = "feedback-request-topic";
    // telegram dispatcher to news predict
    public static final String PREDICT_REQUEST_TOPIC = "predict-request-topic";
    // news feedback to telegram dispatcher
    public static final String FEEDBACK_RESPONSE_TOPIC = "feedback-response-topic";
    // news predict to telegram dispatcher
    public static final String PREDICT_RESPONSE_TOPIC = "predict-response-topic";
    // telegram dispatcher to similar news
    public static final String SIMILAR_REQUEST_TOPIC = "similar-request-topic";
    // similar news to telegram dispatcher
    public static final String SIMILAR_RESPONSE_TOPIC = "similar-response-topic";
    // news parsing and embedding to node
    public static final String NEWS_TOPIC = "news-topic";
}
