package org.example.fileanalisys.infrastructure.web.homework;

import java.util.Date;
import java.util.UUID;

public record UploadHomeworkRequest(UUID author, String task) {
}
