package com.github.vlsidlyarevich.unity.db.service;

import com.github.vlsidlyarevich.unity.common.utils.FileUtils;
import com.github.vlsidlyarevich.unity.db.config.StorageProperties;
import com.github.vlsidlyarevich.unity.db.exception.FileSystemFileNotFoundException;
import com.github.vlsidlyarevich.unity.db.exception.FileSystemStorageException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.stream.Collectors;


@Slf4j
@Service
public class FileSystemStorageService implements StorageService {

    private final Path storeLocation;

    @Autowired
    public FileSystemStorageService(final StorageProperties properties) {
        this.storeLocation = Paths.get(properties.getPath());
    }

    @PostConstruct
    public void init() {
        try {
            Files.createDirectory(storeLocation);
        } catch (IOException e) {
            log.error("Storage initialisation, file already exists:", e.getMessage());
        }
    }

    @Override
    public String store(final MultipartFile file) {
        final String id = UUID.randomUUID().toString();
        try {
            if (file.isEmpty()) {
                log.error("Failed to store empty file " + file.getOriginalFilename());
                throw new FileSystemStorageException("storage.filesystem.file.empty",
                        new Object[]{file.getOriginalFilename()});
            }
            Files.copy(file.getInputStream(), storeLocation.resolve(id));
        } catch (IOException e) {
            throw new FileSystemStorageException(e.getMessage(), e.getCause(),
                    "storage.filesystem.file.storeFail",
                    new Object[]{file.getOriginalFilename()});
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
            throw new FileSystemStorageException(e.getMessage(),
                    "storage.filesystem.files.readFail", e.getCause());
        }
    }

    @Override
    public Path load(final String filename) {
        return storeLocation.resolve(filename);
    }

    @Override
    public Resource loadAsResource(final String filename) {
        try {
            Path file = load(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new FileNotFoundException();
            }
        } catch (MalformedURLException | FileNotFoundException e) {
            throw new FileSystemFileNotFoundException(e.getMessage(),
                    "storage.filesystem.file.readFail",
                    new Object[]{filename});
        }
    }

    @Override
    public Boolean exists(final String filename) {
        try {
            return Files.walk(this.storeLocation, 1)
                    .filter(path -> path.toFile().getName().equals(filename))
                    .findFirst().isPresent();

        } catch (IOException | NoSuchElementException e) {
            log.warn("Can't check existing of file:" + filename);
        }
        return false;
    }

    @Override
    public void deleteAll() {
        FileUtils.cleanDirectory(storeLocation);
    }

    @Override
    public String delete(final String id) {
        try {
            Files.walk(this.storeLocation, 1)
                    .filter(path -> path.toFile().getName().equals(id))
                    .findFirst().get().toFile().delete();

            return id;
        } catch (IOException | NoSuchElementException e) {
            throw new FileSystemStorageException(e.getMessage(), e.getCause(),
                    "storage.filesystem.file.deleteFail", new Object[]{id});
        }
    }
}
