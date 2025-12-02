package org.example.fileanalysis.infrastructure.web.analysis;

import org.example.fileanalysis.application.analysis.WordCloudClient;
import org.example.fileanalysis.infrastructure.web.dto.WordCLoudRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class WordCloudClientImpl implements WordCloudClient {

    private final RestTemplate restTemplate;

    @Value("${wordcloud.baseurl}")
    private String baseUrl;

    public WordCloudClientImpl(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    @Override
    public Resource getWorkCLoud(String text) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<WordCLoudRequest> entity = new HttpEntity<>(WordCLoudRequest.createRequest(text), headers);
        return restTemplate.postForObject(baseUrl + "/wordcloud", entity, Resource.class);
    }
}
