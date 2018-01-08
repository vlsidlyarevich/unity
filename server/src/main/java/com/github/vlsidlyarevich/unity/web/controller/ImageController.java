package com.github.vlsidlyarevich.unity.web.controller;

import com.github.vlsidlyarevich.unity.domain.service.StorageService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/api/v1/image")
public class ImageController {

    private final StorageService storageService;

    @GetMapping(value = "/{id}",
            produces = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity getImage(@PathVariable final String id) {
        Resource resource = storageService.loadAsResource(id);
        return ResponseEntity.ok().body(resource);
    }

    @PostMapping
    public ResponseEntity uploadImage(@RequestParam("file") final MultipartFile file) {
        return ResponseEntity.status(HttpStatus.CREATED).body(storageService.store(file));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteImage(@PathVariable final String id) {
        return ResponseEntity.ok().body(storageService.delete(id));
    }
}
