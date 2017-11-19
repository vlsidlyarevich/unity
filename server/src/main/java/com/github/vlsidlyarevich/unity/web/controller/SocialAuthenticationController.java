package com.github.vlsidlyarevich.unity.web.controller;

import com.github.vlsidlyarevich.unity.web.security.social.service.SocialAuthenticationService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/social")
public class SocialAuthenticationController {

    private final SocialAuthenticationService signupService;

    @GetMapping("/authenticate")
    public RedirectView authenticate(final WebRequest webRequest) {
        return signupService.authenticate(webRequest);
    }
}
