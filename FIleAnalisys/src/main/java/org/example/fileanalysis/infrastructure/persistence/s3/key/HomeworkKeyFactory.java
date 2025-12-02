package org.example.fileanalysis.infrastructure.persistence.s3.key;

import org.example.fileanalysis.domain.model.Homework;
import org.springframework.stereotype.Component;

@Component
public class HomeworkKeyFactory {
    public String buildKeyForHomeworkFile(Homework homework) {
      return "%s/%s/%s".formatted(
              homework.getTask(),
              homework.getAuthor().getUuid(),
              homework.getFilename()
      );
    }

    public String buildKeyForWordCloud(Homework homework) {
        return "%s/%s/%s".formatted(
                homework.getTask(),
                homework.getAuthor().getUuid(),
                "wordcloud.png" //TODO: хардкор
        );
    }
}
