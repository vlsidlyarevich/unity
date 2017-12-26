package com.github.vlsidlyarevich.unity.web.security.social.service;

import com.github.vlsidlyarevich.unity.domain.model.User;
import com.github.vlsidlyarevich.unity.domain.service.SocialUserService;
import com.github.vlsidlyarevich.unity.web.security.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.social.support.URIBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.Cookie;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DefaultSocialAuthenticationService implements SocialAuthenticationService {

    @Value("${security.social.auth-url}")
    private String redirectUrl;
    @Value("${security.social.auth-cookie.name}")
    private String socialAuthenticationCookieName;
    @Value("${security.social.auth-cookie.max-age}")
    private String cookieMaxAge;

    private final SocialUserService socialUserService;
    private final ProviderSignInUtils providerSignInUtils;
    private final TokenService tokenService;

    @Override
    public RedirectView authenticate(final WebRequest request) {
        try {
            final Connection<?> connection = providerSignInUtils.getConnectionFromSession(request);

            final User user = socialUserService.create(connection);

            final String jwt = tokenService.getToken(user.getUsername(), user.getPassword());

            final ServletWebRequest servletWebRequest = (ServletWebRequest) request;

            servletWebRequest.getResponse().addCookie(getSocialAuthenticationCookie(jwt));

            return new RedirectView(URIBuilder.fromUri(redirectUrl)
                    .build().toString(), true);
        } catch (Exception e) {
            return new RedirectView(URIBuilder.fromUri(redirectUrl)
                    .queryParam("success", "false")
                    .build().toString(), true);
        }
    }

    private Cookie getSocialAuthenticationCookie(final String token) {
        final Cookie socialAuthCookie = new Cookie(socialAuthenticationCookieName, token);

        socialAuthCookie.setPath("/");
        socialAuthCookie.setMaxAge(Integer.parseInt(cookieMaxAge));

        return socialAuthCookie;
    }
}
