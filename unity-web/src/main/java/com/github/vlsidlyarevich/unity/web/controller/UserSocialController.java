package com.github.vlsidlyarevich.unity.web.controller;


import com.github.vlsidlyarevich.unity.db.model.UserSocial;
import com.github.vlsidlyarevich.unity.db.service.UserSocialService;
import com.github.vlsidlyarevich.unity.web.converter.ConverterFacade;
import com.github.vlsidlyarevich.unity.web.dto.UserSocialDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user/{id}/social")
public class UserSocialController {

    @Autowired
    private UserSocialService service;

    @Autowired
    private ConverterFacade converterFacade;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity getUserSocialData(@PathVariable String id) {
        UserSocial userSocial = service.findByUserId(id);
        if (userSocial == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(converterFacade.convert(userSocial), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity updateUserSocialData(@PathVariable String id, @RequestBody UserSocialDTO dto) {
        UserSocial userSocial = (UserSocial) converterFacade.convert(dto);
        userSocial.setUserId(id);
        return new ResponseEntity<>(converterFacade.convert(service.create(userSocial)), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteUserSocialDataById(@PathVariable String id) {
        return new ResponseEntity<>(service.delete(id), HttpStatus.OK);
    }
}

