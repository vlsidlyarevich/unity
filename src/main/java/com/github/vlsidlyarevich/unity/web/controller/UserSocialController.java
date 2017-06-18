package com.github.vlsidlyarevich.unity.web.controller;

import com.github.vlsidlyarevich.unity.common.exception.UserNotFoundException;
import com.github.vlsidlyarevich.unity.db.domain.UserSocial;
import com.github.vlsidlyarevich.unity.db.service.UserService;
import com.github.vlsidlyarevich.unity.db.service.UserSocialService;
import com.github.vlsidlyarevich.unity.web.converter.ConverterFacade;
import com.github.vlsidlyarevich.unity.web.dto.UserSocialDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user/{id}/social")
public class UserSocialController {

    private final UserSocialService service;

    private final UserService userService;

    private final ConverterFacade converterFacade;

    @Autowired
    public UserSocialController(final UserSocialService service,
                                final UserService userService,
                                final ConverterFacade converterFacade) {
        this.service = service;
        this.userService = userService;
        this.converterFacade = converterFacade;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity getUserSocialData(@PathVariable final String id) {
        final UserSocial userSocial = service.findByUserId(id);
        if (userSocial == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(converterFacade.convert(userSocial), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity updateUserSocialData(@PathVariable final String id,
                                               @RequestBody final UserSocialDTO dto) {
        final UserSocial userSocial = (UserSocial) converterFacade.convert(dto);
        userSocial.setUserId(id);

        if (userService.find(userSocial.getUserId()) != null) {
            return new ResponseEntity<>(converterFacade
                    .convert(service.update(id, userSocial)), HttpStatus.OK);
        } else {
            throw new UserNotFoundException("User with user id: "
                    + userSocial.getUserId() + " not found");
        }
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity deleteUserSocialDataByUserId(@PathVariable final String id) {
        return new ResponseEntity<>(service.deleteByUserId(id), HttpStatus.OK);
    }
}

