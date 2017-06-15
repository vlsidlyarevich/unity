package com.github.vlsidlyarevich.unity.common.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.nio.file.Path;

@Slf4j
public final class FileUtils {

    private FileUtils() {

    }

    public static void cleanDirectory(final Path pathToDirectory) {
        if (pathToDirectory != null) {
            for (File file : pathToDirectory.toFile().listFiles()) {
                file.delete();
            }
        }
    }
}
