package com.github.vlsidlyarevich.unity.web.controller;

import com.github.vlsidlyarevich.unity.web.security.facade.AuthenticationFacade;
import com.github.vlsidlyarevich.unity.domain.model.User;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/api/v1/user/me")
public class CurrentUserController {

    private final AuthenticationFacade authenticationFacade;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity getCurrentUser() {
        final User currentUser =
                (User) authenticationFacade.getAuthentication().getDetails();
        return new ResponseEntity<>(currentUser, HttpStatus.OK);
    }
}
