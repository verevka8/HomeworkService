package org.example.fileanalysis.infrastructure.persistence.s3;

import org.example.fileanalysis.domain.model.Homework;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Object;

import java.io.IOException;
import java.util.List;

@Service
public class YandexS3Service {

    @Value("${yandex.cloud.s3.bucket}")
    private String bucket;

    private final S3Client s3Client;

    public YandexS3Service(S3Client s3Client) {
        this.s3Client = s3Client;
    }

    public String upload(Homework homework, MultipartFile file) throws IOException {
        String key = homework.getTask() + "/" + homework.getAuthor().getUuid() + "/" + homework.getFilename();
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

        return key;
    }

    public List<S3Object> getAllHomeworksOnTask(String task) {
        return s3Client.listObjectsV2(builder -> builder.bucket(bucket).prefix(task + "/")).contents();
    }

    public String getObjectAsText(String key) {
        return s3Client.getObjectAsBytes(r -> r.bucket(bucket).key(key)).asUtf8String();
    }
}
