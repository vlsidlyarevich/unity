package com.github.vlsidlyarevich.unity.web.controller;

import com.github.vlsidlyarevich.unity.auth.service.TokenService;
import com.github.vlsidlyarevich.unity.web.dto.TokenDTO;
import com.github.vlsidlyarevich.unity.web.dto.LoginDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    @Autowired
    private TokenService tokenService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> authenticate(LoginDTO dto) {
        String token = tokenService.getToken(dto.getUserName(), dto.getPassword());
        if (token != null) {
            TokenDTO response = new TokenDTO();
            response.setToken(token);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            //FIXME
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
