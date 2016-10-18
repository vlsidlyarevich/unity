package com.github.vlsidlyarevich.unity.service.impl;

import com.github.vlsidlyarevich.unity.service.StorageService;
import com.github.vlsidlyarevich.unity.utils.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Created by vladislav on 10/11/16.
 */
@Slf4j
@Service
public class FileSystemStorageService implements StorageService {

    private final Path storeLocation = Paths.get("files");

    @PostConstruct
    public void init() {
        try {
            Files.createDirectory(storeLocation);
        } catch (IOException e) {
            log.error("Could not initialize storage", e);
        }
    }

    @Override
    public String store(MultipartFile file) {
        String id = UUID.randomUUID().toString();
        try {
            if (file.isEmpty()) {
                log.error("Failed to store empty file " + file.getOriginalFilename());
                return null;
            }
            Files.copy(file.getInputStream(), storeLocation.resolve(id));
        } catch (IOException e) {
            log.error("Failed to store file " + file.getOriginalFilename(), e);
            return null;
        }
        return id;
    }

    @Override
    public List<Path> loadAll() {
        try {
            return Files.walk(this.storeLocation, 1)
                    .filter(path -> !path.equals(this.storeLocation))
                    .map(this.storeLocation::relativize).collect(Collectors.toList());
        } catch (IOException e) {
            log.error("Failed to read stored files", e);
            return null;
        }
    }

    @Override
    public Path load(String filename) {
        return storeLocation.resolve(filename);
    }

    @Override
    public Resource loadAsResource(String filename) {
        try {
            Path file = load(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                log.error("Could not read file: " + filename);
                return null;
            }
        } catch (MalformedURLException e) {
            log.error("Could not read file: " + filename, e);
            return null;
        }
    }

    @Override
    public void deleteAll() {
        FileUtils.cleanDirectory(storeLocation);
    }
}
