package org.example.fileanalysis.infrastructure.web.api.analysis;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.fileanalysis.application.analysis.in.HomeworkAnalysisService;
import org.example.fileanalysis.application.dto.ReportResponse;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/api")
@Tag(name = "Анализ домашних заданий", description = "Запуск анализа и получение отчетов")
public class HomeworkController {
    private final HomeworkAnalysisService service;

    public HomeworkController(HomeworkAnalysisService service) {
        this.service = service;
    }

    @GetMapping(value = "/analysis_homework")
    @Operation(
            summary = "Запуск анализа конкретного задания",
            description = "Инициирует проверку домашнего задания на предмет схожести",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Анализ запущен"),
                    @ApiResponse(responseCode = "404", description = "Задание не найдено")
            }
    )
    public void analysisHomework(@Parameter(description = "UUID загруженного домашнего задания", required = true)
                                 @RequestParam UUID homeworkId) throws IOException {
        service.analysis(homeworkId);
    }

    @GetMapping(value = "/homeworks/{task}/report")
    @Operation(
            summary = "Получить отчет по задаче",
            description = "Возвращает список возможных плагиаторов для указанной задачи",
            responses = @ApiResponse(responseCode = "200", description = "Отчет найден")
    )
    public ReportResponse getReportOfTask(@Parameter(description = "Название задачи", required = true)
                                          @PathVariable String task) {
        return service.getTaskReport(task);
    }
}