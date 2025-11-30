package org.example.fileanalysis.application.analysis;

import org.example.fileanalysis.application.dto.ReportResponse;

import java.util.UUID;

public interface HomeworkAnalysisService {
    void analysis(UUID homeworkId);
    ReportResponse getTaskReport(String task);
}
