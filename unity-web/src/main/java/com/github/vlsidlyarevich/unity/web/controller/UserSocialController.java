package com.github.vlsidlyarevich.unity.web.controller;


import com.github.vlsidlyarevich.unity.db.model.UserSocial;
import com.github.vlsidlyarevich.unity.db.service.UserSocialService;
import com.github.vlsidlyarevich.unity.web.converter.ConverterFacade;
import com.github.vlsidlyarevich.unity.web.dto.UserSocialDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/user/{id}/social")
public class UserSocialController {

    @Autowired
    private UserSocialService service;

    @Autowired
    private ConverterFacade converterFacade;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity getUserSocialData(@PathVariable String id) {
        return new ResponseEntity<>(service.find(id), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity addUserSocialData(@PathVariable String id, @Valid @RequestBody UserSocialDTO dto) {
        UserSocial userSocial = (UserSocial) converterFacade.convert(dto);
        userSocial.setUserId(id);
        return new ResponseEntity<>(service.create(userSocial), HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity updateUserSocial(@Valid @RequestBody UserSocialDTO dto) {
        return new ResponseEntity<>(service.update((UserSocial) converterFacade.convert(dto)), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteUserById(@PathVariable String id) {
        return new ResponseEntity<>(service.delete(id), HttpStatus.OK);
    }
}

