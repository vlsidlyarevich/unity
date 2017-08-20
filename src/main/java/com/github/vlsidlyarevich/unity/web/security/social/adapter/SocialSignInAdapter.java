package com.github.vlsidlyarevich.unity.web.security.social.adapter;

import com.github.vlsidlyarevich.unity.web.security.service.TokenService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.SignInAdapter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.Cookie;

@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class SocialSignInAdapter implements SignInAdapter {

    private static final String SOCIAL_AUTH_URL = "/#/social-auth";
    private static final String SOCIAL_AUTHENTICATION_COOKIE_NAME = "social-authentication";
    private static final Integer COOKIE_MAX_AGE = 10;

    private final UserDetailsService userDetailsService;

    private final TokenService tokenService;

    @Override
    public String signIn(final String userId, final Connection<?> connection, final NativeWebRequest request) {
        final UserDetails user = userDetailsService.loadUserByUsername(userId);

        final String jwt = tokenService.getToken(user.getUsername(), user.getPassword());

        final ServletWebRequest servletWebRequest = (ServletWebRequest) request;

        servletWebRequest.getResponse().addCookie(getSocialAuthenticationCookie(jwt));

        return SOCIAL_AUTH_URL;
    }

    private Cookie getSocialAuthenticationCookie(final String token) {
        final Cookie socialAuthCookie = new Cookie(SOCIAL_AUTHENTICATION_COOKIE_NAME, token);

        socialAuthCookie.setPath("/");
        socialAuthCookie.setMaxAge(COOKIE_MAX_AGE);

        return socialAuthCookie;
    }
}
