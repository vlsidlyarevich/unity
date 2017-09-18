package com.github.vlsidlyarevich.unity.web.controller;

import com.github.vlsidlyarevich.unity.domain.model.User;
import com.github.vlsidlyarevich.unity.domain.service.UserService;
import com.github.vlsidlyarevich.unity.web.dto.user.UserRequest;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping(path = "/api/v1/signup", produces = MediaType.APPLICATION_JSON_VALUE)
public class SignUpController {

    private final UserService service;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity signUp(@Valid @RequestBody final UserRequest dto) {
        return ResponseEntity.ok(service.create(User.fromDTO(dto)));
    }
}
