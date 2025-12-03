package org.example.filestoring.infrastructure.web.api.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.filestoring.application.homework.in.HomeworkService;
import org.example.filestoring.infrastructure.web.api.homework.dto.UploadHomeworkRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api")
@Tag(name = "Домашние задания", description = "Управление загрузкой файлов домашних заданий")
public class HomeworkController {
    private final HomeworkService service;

    public HomeworkController(HomeworkService service){
        this.service = service;
    }

    @Operation(
            summary = "Загрузка домашнего задания",
            description = "Принимает метаданные задания и файл с решением в формате multipart/form-data",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Файл загружен и поставлен на анализ"),
                    @ApiResponse(responseCode = "400", description = "Некорректные данные запроса"),
                    @ApiResponse(responseCode = "500", description = "Ошибка сохранения или отправки на анализ")
            }
    )
    @PostMapping(value = "/homework", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void upload(
            @Parameter(description = "Метаданные загружаемого задания", required = true)
            @RequestPart("data") UploadHomeworkRequest data,
            @Parameter(
                    description = "Файл домашнего задания",
                    required = true,
                    content = @Content(mediaType = MediaType.MULTIPART_FORM_DATA_VALUE,
                            schema = @Schema(type = "string", format = "binary"))
            )
            @RequestPart("file") MultipartFile file) throws IOException {
        service.createHomework(data.author(),data.task(), file);
    }
}
