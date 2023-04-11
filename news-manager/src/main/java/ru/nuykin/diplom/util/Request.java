package ru.nuykin.diplom.util;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

@Service
public class Request {
    @Value("${urls.embeddingRequestUrl}")
    String embeddingRequestUrl;
    private final RestTemplate restTemplate = new RestTemplate();

    public String getEmbeddingRequest(String title) {
        ParameterizedTypeReference<Map<String, String>> responseType =
                new ParameterizedTypeReference<>() {};

        RequestEntity<Void> request = RequestEntity.get(embeddingRequestUrl)
                .accept(MediaType.APPLICATION_JSON).build();
        Map<String, String> jsonDictionary = restTemplate.exchange(request, responseType).getBody();


        return jsonDictionary.get("result");
    }
}
