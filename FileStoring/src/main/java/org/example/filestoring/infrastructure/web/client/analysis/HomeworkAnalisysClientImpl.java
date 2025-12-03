package org.example.filestoring.infrastructure.web.client.analysis;

import org.example.filestoring.application.homework.out.HomeworkAnalisysClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.UUID;

@Component
public class HomeworkAnalisysClientImpl implements HomeworkAnalisysClient {

    private final RestTemplate restTemplate;

    @Value("${fileanalisys}")
    private String baseUrl;

    public HomeworkAnalisysClientImpl(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    @Override
    public void analyzeHomework(UUID homeworkId) {
        String url = UriComponentsBuilder.fromUriString(baseUrl + "/api/analysis_homework")
                .queryParam("homeworkId", homeworkId)
                .toUriString();
        restTemplate.getForObject(url, Void.class);
    }
}