package com.github.vlsidlyarevich.unity.web.security.social.service;

import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.view.RedirectView;

public interface SocialAuthenticationService {

    RedirectView authenticate(WebRequest request);
}
