package com.github.vlsidlyarevich.unity.web.controller;

import com.github.vlsidlyarevich.unity.web.dto.LoginDTO;
import com.github.vlsidlyarevich.unity.web.dto.TokenDTO;
import com.github.vlsidlyarevich.unity.web.security.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    private final TokenService tokenService;

    @Autowired
    public AuthenticationController(final TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity authenticate(@Valid @RequestBody final LoginDTO dto) {
        return new ResponseEntity<>(new TokenDTO(tokenService.getToken(dto.getUsername(),
                dto.getPassword())), HttpStatus.OK);
    }
}
