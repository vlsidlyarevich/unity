package com.github.vlsidlyarevich.unity.web.controller;

import com.github.vlsidlyarevich.unity.domain.model.UserSocial;
import com.github.vlsidlyarevich.unity.domain.service.UserSocialService;
import com.github.vlsidlyarevich.unity.web.dto.user.UserSocialRequest;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping(path = "/api/v1/user/{id}/social", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserSocialController {

    private final UserSocialService socialService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity getUserSocialData(@PathVariable final String id) {
        final UserSocial userSocial = socialService.findByUserId(id);

        return ResponseEntity.ok(UserSocialRequest.fromDomain(userSocial));
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity updateUserSocialData(@PathVariable final String id,
                                               @RequestBody final UserSocialRequest dto) {
        return ResponseEntity.ok(UserSocialRequest
                .fromDomain(socialService.update(id, UserSocial.fromDTO(dto))));
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity deleteUserSocialDataByUserId(@PathVariable final String id) {
        return ResponseEntity.ok(socialService.deleteByUserId(id));
    }
}

