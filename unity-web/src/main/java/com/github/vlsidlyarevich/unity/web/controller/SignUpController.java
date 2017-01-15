package com.github.vlsidlyarevich.unity.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/signup")
public class SignUpController {

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> get() {
        return new ResponseEntity<>("dat is sign up", HttpStatus.OK);
    }
}
