package com.github.vlsidlyarevich.unity.web.security.social.service;

import com.github.vlsidlyarevich.unity.domain.service.SocialUserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.social.support.URIBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.view.RedirectView;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class DefaultSocialSignupService implements SocialSignupService {

    private static final String SOCIAL_REGISTER_URL = "/#/social-register/";

    private final SocialUserService socialUserService;

    private final ProviderSignInUtils providerSignInUtils;

    @Override
    public RedirectView signup(final WebRequest request) {
        final Connection<?> connection = providerSignInUtils.getConnectionFromSession(request);

        socialUserService.create(connection);

        return new RedirectView(URIBuilder.fromUri(String
                .format("%s%s", SOCIAL_REGISTER_URL, connection.getKey().getProviderId()))
                .queryParam("success", "true")
                .build().toString(), true);
    }
}
