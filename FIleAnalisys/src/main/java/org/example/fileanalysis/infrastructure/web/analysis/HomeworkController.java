package org.example.fileanalysis.infrastructure.web.analysis;

import org.example.fileanalysis.application.analysis.HomeworkAnalysisService;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api")
public class HomeworkController {
    private final HomeworkAnalysisService service;

    public HomeworkController(HomeworkAnalysisService service){
        this.service = service;
    }
    @GetMapping(value = "/analysis_homework")
    public void analysisHomework(@RequestParam UUID homeworkId)  {
        service.analysis(homeworkId);
    }
}
