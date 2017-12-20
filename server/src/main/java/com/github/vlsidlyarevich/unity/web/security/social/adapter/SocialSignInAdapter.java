package com.github.vlsidlyarevich.unity.web.security.social.adapter;

import com.github.vlsidlyarevich.unity.web.security.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.SignInAdapter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.Cookie;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SocialSignInAdapter implements SignInAdapter {

    @Value("security.social.auth-url")
    private String socialAuthUrl;
    @Value("security.social.auth-cookie.name")
    private String socialAuthenticationCookieName;
    @Value("security.social.auth-cookie.max-age")
    private String cookieMaxAge;

    private final UserDetailsService userDetailsService;
    private final TokenService tokenService;

    @Override
    public String signIn(final String userId, final Connection<?> connection, final NativeWebRequest request) {
        final UserDetails user = userDetailsService.loadUserByUsername(userId);

        final String jwt = tokenService.getToken(user.getUsername(), user.getPassword());

        final ServletWebRequest servletWebRequest = (ServletWebRequest) request;

        servletWebRequest.getResponse().addCookie(getSocialAuthenticationCookie(jwt));

        return socialAuthUrl;
    }

    private Cookie getSocialAuthenticationCookie(final String token) {
        final Cookie socialAuthCookie = new Cookie(socialAuthenticationCookieName, token);

        socialAuthCookie.setPath("/");
        socialAuthCookie.setMaxAge(Integer.parseInt(cookieMaxAge));

        return socialAuthCookie;
    }
}
