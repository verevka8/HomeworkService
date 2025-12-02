package org.example.filestoring.infrastructure.persistence.s3.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.S3Configuration;

import java.net.URI;

@Configuration
public class YandexS3Config {

    @Value("${yandex.cloud.s3.endpoint}")
    private String endpoint;

    @Value("${yandex.cloud.s3.region}")
    private String region;

    @Value("${yandex.cloud.s3.access-key}")
    private String accessKey;

    @Value("${yandex.cloud.s3.secret-key}")
    private String secretKey;

    @Bean
    public S3Client yandexS3Client() {
        S3Configuration s3Config = S3Configuration.builder()
                .pathStyleAccessEnabled(true)
                .build();

        return S3Client.builder()
                .credentialsProvider(
                        StaticCredentialsProvider.create(
                                AwsBasicCredentials.create(accessKey, secretKey)
                        )
                )
                .endpointOverride(URI.create(endpoint))
                .region(Region.of(region))
                .serviceConfiguration(s3Config)
                .build();
    }
}