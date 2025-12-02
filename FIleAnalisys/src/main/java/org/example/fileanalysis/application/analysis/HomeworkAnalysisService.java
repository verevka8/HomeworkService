package org.example.fileanalysis.application.analysis;

import org.example.fileanalysis.application.dto.ReportResponse;

import java.io.IOException;
import java.util.UUID;

public interface HomeworkAnalysisService {
    void analysis(UUID homeworkId) throws IOException;
    ReportResponse getTaskReport(String task);
}
