package org.example.fileanalysis.domain.repository;

import org.example.fileanalysis.domain.model.Plagiarism;

public interface PlagiarismRepository {
    void savePlagiarism(Plagiarism plagiarism);
}
