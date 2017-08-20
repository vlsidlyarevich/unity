package com.github.vlsidlyarevich.unity.web.controller;

import com.github.vlsidlyarevich.unity.domain.service.StorageService;
import lombok.AllArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/image")
public class ImageController {

    private final StorageService storageService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity getImage(@PathVariable final String id) {
        Resource resource = storageService.loadAsResource(id);
        return new ResponseEntity<>(resource, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity uploadImage(@RequestParam("file") final MultipartFile file) {
        return new ResponseEntity<>(storageService.store(file), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteImage(@PathVariable final String id) {
        return new ResponseEntity<>(storageService.delete(id), HttpStatus.OK);
    }
}
