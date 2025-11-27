package org.example.fileanalysis.domain.service;

import org.springframework.stereotype.Component;

@Component
public class PlagiarismChecker {

    public boolean isPlagiarized(String homeworkA, String homeworkB) {
        return homeworkA.equals(homeworkB);
    }
}
