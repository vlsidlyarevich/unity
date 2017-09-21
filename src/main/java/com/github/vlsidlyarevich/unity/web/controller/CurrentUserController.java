package com.github.vlsidlyarevich.unity.web.controller;

import com.github.vlsidlyarevich.unity.domain.model.User;
import com.github.vlsidlyarevich.unity.web.security.facade.AuthenticationFacade;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping(path = "/api/v1/user/me", produces = MediaType.APPLICATION_JSON_VALUE)
public class CurrentUserController {

    private final AuthenticationFacade authenticationFacade;

    @GetMapping
    public ResponseEntity getCurrentUser() {
        final User currentUser = (User) authenticationFacade.getAuthentication().getDetails();

        return ResponseEntity.ok(currentUser);
    }
}
