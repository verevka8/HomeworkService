package org.example.fileanalysis.application.analysis;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface WordCloudClient {
    Resource getWorkCLoud(String text);
}
