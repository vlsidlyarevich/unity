package com.github.vlsidlyarevich.unity.web.security.social.service;

import com.github.vlsidlyarevich.unity.domain.model.User;
import com.github.vlsidlyarevich.unity.domain.service.SocialUserService;
import com.github.vlsidlyarevich.unity.web.security.facade.AuthenticationFacade;
import com.github.vlsidlyarevich.unity.web.security.service.TokenService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.social.support.URIBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.Cookie;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class DefaultSocialAuthenticationService implements SocialAuthenticationService {

    private static final String REDIRECT_URL = "/social-auth";

    //FIXME to properties
    private static final String SOCIAL_AUTHENTICATION_COOKIE_NAME = "social-authentication";
    private static final Integer COOKIE_MAX_AGE = 10;

    private final SocialUserService socialUserService;
    private final ProviderSignInUtils providerSignInUtils;
    private final TokenService tokenService;

    @Override
    public RedirectView authenticate(final WebRequest request) {
        final Connection<?> connection = providerSignInUtils.getConnectionFromSession(request);

        final User user = socialUserService.create(connection);

        final String jwt = tokenService.getToken(user.getUsername(), user.getPassword());

        final ServletWebRequest servletWebRequest = (ServletWebRequest) request;

        servletWebRequest.getResponse().addCookie(getSocialAuthenticationCookie(jwt));

        return new RedirectView(URIBuilder.fromUri(REDIRECT_URL)
                .build().toString(), true);
    }

    private Cookie getSocialAuthenticationCookie(final String token) {
        final Cookie socialAuthCookie = new Cookie(SOCIAL_AUTHENTICATION_COOKIE_NAME, token);

        socialAuthCookie.setPath("/");
        socialAuthCookie.setMaxAge(COOKIE_MAX_AGE);

        return socialAuthCookie;
    }
}
