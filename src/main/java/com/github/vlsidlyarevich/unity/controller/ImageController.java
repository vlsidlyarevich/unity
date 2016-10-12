package com.github.vlsidlyarevich.unity.controller;

import com.github.vlsidlyarevich.unity.model.Image;
import com.github.vlsidlyarevich.unity.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by vladislav on 10/11/16.
 */
@RestController
@RequestMapping("/api/images")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity getImage(@PathVariable Long id) {
        return new ResponseEntity<>("https://lh3.googleusercontent.com/-KaDIOpJ3DjE/AAAAAAAAAAI/AAAAAAAAAAA/Wx0027iseXM/photo.jpg", HttpStatus.OK);
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public ResponseEntity uploadImage(@RequestParam("file") MultipartFile file) {
        return new ResponseEntity<>(imageService.create(new Image(file)), HttpStatus.OK);
    }
}
