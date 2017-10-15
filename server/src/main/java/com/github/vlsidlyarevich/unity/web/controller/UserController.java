package com.github.vlsidlyarevich.unity.web.controller;

import com.github.vlsidlyarevich.unity.domain.model.User;
import com.github.vlsidlyarevich.unity.domain.service.UserService;
import com.github.vlsidlyarevich.unity.web.dto.user.UserRequest;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping(path = "/api/v1/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    private final UserService service;

    @GetMapping
    public ResponseEntity getAllUsers() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity getUserById(@PathVariable final String id) {
        return ResponseEntity.ok(service.find(id));
    }

    @PostMapping
    public ResponseEntity addUser(@Valid @RequestBody final UserRequest dto) {
        return new ResponseEntity<>(service.create(User.fromDTO(dto)), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity updateUserById(@PathVariable final String id,
                                         @Valid @RequestBody final UserRequest dto) {
        return ResponseEntity.ok().body(service.update(id, User.fromDTO(dto)));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteUserById(@PathVariable final String id) {
        return ResponseEntity.ok().body(service.delete(id));
    }
}
