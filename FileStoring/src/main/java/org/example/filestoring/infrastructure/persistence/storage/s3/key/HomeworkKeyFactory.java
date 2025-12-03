package org.example.filestoring.infrastructure.persistence.storage.s3.key;

import org.example.filestoring.domain.model.Homework;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class HomeworkKeyFactory {
    public String buildKey(Homework homework) {
      return "%s/%s/%s".formatted(
              homework.getTask(),
              homework.getAuthor().getUuid(),
              homework.getFilename()
      );
    }
}
