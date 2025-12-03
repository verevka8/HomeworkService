package org.example.filestoring.infrastructure.web.homework;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.UUID;


public record UploadHomeworkRequest(UUID author, String task) {
}
