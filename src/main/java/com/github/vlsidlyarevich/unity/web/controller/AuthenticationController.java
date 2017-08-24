package com.github.vlsidlyarevich.unity.web.controller;

import com.github.vlsidlyarevich.unity.web.dto.LoginDTO;
import com.github.vlsidlyarevich.unity.web.dto.TokenDTO;
import com.github.vlsidlyarevich.unity.web.security.service.TokenService;
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
@RequestMapping(path = "/api/v1/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthenticationController {

    private final TokenService tokenService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity authenticate(@Valid @RequestBody final LoginDTO dto) {
        return ResponseEntity.ok(new TokenDTO(tokenService.getToken(dto.getUsername(),
                dto.getPassword())));
    }
}