package com.github.vlsidlyarevich.unity.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;


@Slf4j
public final class FileUtils {

    private FileUtils() {

    }

    public static File multipartToFile(MultipartFile multipart) {
        try {
            File convFile = new File(multipart.getName());
            multipart.transferTo(convFile);
            return convFile;

        } catch (IllegalStateException | IOException e) {
            log.error("Error converting multipart to file!", e);
        }
        return null;
    }

    public static File multipartToFile(MultipartFile multipart, String fileName) {
        try {
            File convFile = new File(fileName);
            multipart.transferTo(convFile);
            return convFile;

        } catch (IllegalStateException | IOException e) {
            log.error("Error converting multipart to file!", e);
        }
        return null;
    }

    public static void cleanDirectory(Path pathToDirectory) {
        for (File file : pathToDirectory.toFile().listFiles()) {
            file.delete();
        }
    }

}
