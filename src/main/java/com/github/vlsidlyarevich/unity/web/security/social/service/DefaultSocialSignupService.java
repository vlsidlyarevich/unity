package com.github.vlsidlyarevich.unity.web.security.social.service;

import lombok.AllArgsConstructor;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.social.support.URIBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.view.RedirectView;

@Service
@AllArgsConstructor
public class DefaultSocialSignupService implements SocialSignupService {

    private final SocialUserService socialUserService;

    private final ProviderSignInUtils providerSignInUtils;

    @Override
    public RedirectView signup(final WebRequest request, final String language) {
        try {
            Connection<?> connection = providerSignInUtils.getConnectionFromSession(request);
            socialUserService.create(connection, language.replace("\"", ""));
            return new RedirectView(URIBuilder.fromUri("/#/social-register/" + connection.getKey().getProviderId())
                    .queryParam("success", "true")
                    .build().toString(), true);
        } catch (Exception e) {
//            log.error("Exception creating social user: ", e);
            return new RedirectView(URIBuilder.fromUri("/#/social-register/no-provider")
                    .queryParam("success", "false")
                    .build().toString(), true);
        }
    }
}
