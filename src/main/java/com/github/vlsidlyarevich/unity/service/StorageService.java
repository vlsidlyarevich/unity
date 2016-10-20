package com.github.vlsidlyarevich.unity.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.List;

/**
 * Created by vladislav on 10/11/16.
 */
public interface StorageService {

    String store(MultipartFile file);

    List<Path> loadAll();

    Path load(String filename);

    Resource loadAsResource(String filename);

    void deleteAll();

    String delete(String id);
}
