package com.github.vlsidlyarevich.unity.web.controller;

import com.github.vlsidlyarevich.unity.db.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity getCurrentUser() {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getDetails();
        return new ResponseEntity<>(currentUser, HttpStatus.OK);
    }
}
