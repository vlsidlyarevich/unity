package com.github.vlsidlyarevich.unity.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

@Slf4j
public final class FileUtils {

    private FileUtils() {

    }

    public static Optional<File> multipartToFile(final MultipartFile multipart) {
        try {
            File convFile = new File(multipart.getName());
            multipart.transferTo(convFile);
            return Optional.of(convFile);

        } catch (IllegalStateException | IOException e) {
            log.error("Error converting multipart to file!", e);
        }
        return Optional.empty();
    }

    public static Optional<File> multipartToFile(final MultipartFile multipart,
                                                 final String fileName) {
        try {
            File convFile = new File(fileName);
            multipart.transferTo(convFile);
            return Optional.of(convFile);

        } catch (IllegalStateException | IOException e) {
            log.error("Error converting multipart to file!", e);
        }
        return Optional.empty();
    }

    public static void cleanDirectory(final Path pathToDirectory) {
        for (File file : pathToDirectory.toFile().listFiles()) {
            file.delete();
        }
    }

}
