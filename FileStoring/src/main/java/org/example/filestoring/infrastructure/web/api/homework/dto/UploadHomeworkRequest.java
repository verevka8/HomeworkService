package org.example.filestoring.infrastructure.web.api.homework.dto;

import java.util.UUID;


public record UploadHomeworkRequest(UUID author, String task) {
}
