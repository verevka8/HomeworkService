package org.example.filestoring.infrastructure.web.homework;

import java.util.UUID;

public record UploadHomeworkRequest(UUID author, String task) {
}
