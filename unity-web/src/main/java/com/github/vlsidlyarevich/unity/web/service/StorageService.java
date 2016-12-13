package com.github.vlsidlyarevich.unity.web.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.List;


public interface StorageService {

    String store(MultipartFile file);

    List<Path> loadAll();

    Path load(String filename);

    Resource loadAsResource(String filename);

    Boolean exists(String filename);

    void deleteAll();

    String delete(String id);
}
