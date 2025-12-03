package org.example.fileanalysis.domain.repository;

import org.example.fileanalysis.domain.model.Plagiarism;

import java.util.List;

public interface PlagiarismRepository {
    void savePlagiarism(Plagiarism plagiarism);
    List<Plagiarism> getPlagiarismByTask(String task);
}
