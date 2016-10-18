package com.github.vlsidlyarevich.unity.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * Created by vladislav on 10/18/16.
 */
@Slf4j
public final class FileUtils {

    private FileUtils() {

    }

    public static File multipartToFile(MultipartFile multipart) {
        try {
            File convFile = new File(multipart.getOriginalFilename());
            multipart.transferTo(convFile);
            return convFile;

        } catch (IllegalStateException | IOException e) {
            log.error("Error converting multipart to file!", e);
        }
        return null;
    }

}
