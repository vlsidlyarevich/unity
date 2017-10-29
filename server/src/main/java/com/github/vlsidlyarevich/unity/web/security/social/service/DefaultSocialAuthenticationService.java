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
public class DefaultSocialAuthenticationService implements SocialAuthenticationService {

    private static final String REDIRECT_URL = "/";

    private final SocialUserService socialUserService;
    private final ProviderSignInUtils providerSignInUtils;

    @Override
    public RedirectView authenticate(final WebRequest request) {
        final Connection<?> connection = providerSignInUtils.getConnectionFromSession(request);

        socialUserService.create(connection);

        return new RedirectView(URIBuilder.fromUri(REDIRECT_URL)
                .queryParam("success", "true")
                .build().toString(), true);
    }
}
