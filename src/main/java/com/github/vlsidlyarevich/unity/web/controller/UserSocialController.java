package com.github.vlsidlyarevich.unity.web.controller;

import com.github.vlsidlyarevich.unity.domain.model.UserSocial;
import com.github.vlsidlyarevich.unity.domain.service.UserSocialService;
import com.github.vlsidlyarevich.unity.web.dto.UserSocialDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/user/{id}/social")
public class UserSocialController {

    private final UserSocialService service;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity getUserSocialData(@PathVariable final String id) {
        final UserSocial userSocial = service.findByUserId(id);
        return new ResponseEntity<>(UserSocialDTO.fromDomain(userSocial), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity updateUserSocialData(@PathVariable final String id,
                                               @RequestBody final UserSocialDTO dto) {
        return new ResponseEntity<>(
                UserSocialDTO.fromDomain(service
                        .update(id, UserSocial.fromDTO(dto))), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity deleteUserSocialDataByUserId(@PathVariable final String id) {
        return new ResponseEntity<>(service.deleteByUserId(id), HttpStatus.OK);
    }
}

