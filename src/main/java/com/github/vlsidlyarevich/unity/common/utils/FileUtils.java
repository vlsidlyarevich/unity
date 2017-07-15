package com.github.vlsidlyarevich.unity.common.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Optional;

@Slf4j
public final class FileUtils {

    private FileUtils() {

    }

    public static void cleanDirectory(final Path pathToDirectory) {
    Optional.ofNullable(pathToDirectory)
            .ifPresent(path ->
                    Arrays.stream(pathToDirectory.toFile().listFiles())
                            .forEach(File::delete)
            );
}
}
