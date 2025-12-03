package org.example.fileanalysis.application.dto;

import java.util.List;

public class ReportResponse {
    private List<PlagiatorsReport> plagiarists;
    private String task;


    public ReportResponse(List<PlagiatorsReport> plagiarists, String task) {
        this.plagiarists = plagiarists;
        this.task = task;
    }

    public List<PlagiatorsReport> getPlagiarisms() {
        return plagiarists;
    }

    public void setPlagiarisms(List<PlagiatorsReport> plagiarists) {
        this.plagiarists = plagiarists;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }
}
