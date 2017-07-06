package com.github.vlsidlyarevich.unity.common.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Objects;

@Slf4j
public final class FileUtils {

    private FileUtils() {

    }

    public static void cleanDirectory(final Path pathToDirectory) {
        if (Objects.nonNull(pathToDirectory)
                && Objects.nonNull(pathToDirectory.toFile())) {
            Arrays.stream(pathToDirectory.toFile().listFiles())
                    .forEach(File::delete);
        }
    }
}
