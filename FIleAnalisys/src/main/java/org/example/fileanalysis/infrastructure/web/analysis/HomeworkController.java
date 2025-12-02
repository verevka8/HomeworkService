package org.example.fileanalysis.infrastructure.web.analysis;

import org.example.fileanalysis.application.analysis.HomeworkAnalysisService;
import org.example.fileanalysis.application.dto.ReportResponse;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class HomeworkController {
    private final HomeworkAnalysisService service;

    public HomeworkController(HomeworkAnalysisService service){
        this.service = service;
    }
    @GetMapping(value = "/analysis_homework")
    public void analysisHomework(@RequestParam UUID homeworkId) throws IOException {
        service.analysis(homeworkId);
    }

    @GetMapping(value = "/homeworks/{task}/report")
    public ReportResponse getReportOfTask(@PathVariable String task)  {
        return service.getTaskReport(task);
    }
}
