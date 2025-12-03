package org.example.filestoring.infrastructure.persistence.storage.s3;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;

@Service
public class YandexS3Service {

    private final S3Client s3Client;

    public YandexS3Service(S3Client s3Client){
        this.s3Client = s3Client;
    }

    @Value("${yandex.cloud.s3.bucket}")
    private String bucket;

    public void uploadFile(String key, MultipartFile file) throws IOException {
        PutObjectRequest request = PutObjectRequest.builder()
                .bucket(bucket)
                .key(key)
                .contentType(file.getContentType())
                .contentLength(file.getSize())
                .build();

        s3Client.putObject(
                request,
                RequestBody.fromInputStream(file.getInputStream(), file.getSize())
        );
    }
}
