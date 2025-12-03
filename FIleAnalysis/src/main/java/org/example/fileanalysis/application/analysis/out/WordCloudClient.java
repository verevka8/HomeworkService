package org.example.fileanalysis.application.analysis.out;

import org.springframework.core.io.Resource;

public interface WordCloudClient {
    Resource getWorkCLoud(String text);
}
