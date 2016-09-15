package com.github.vlsidlyarevich.unity.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by vlad on 14.09.16.
 */
@RestController
public class HelloController {

    @RequestMapping("/hello")
    public ResponseEntity sayHello() {
        return new ResponseEntity("Hello!", HttpStatus.OK);
    }

}
