package com.github.vlsidlyarevich.unity.auth.service;

import com.github.vlsidlyarevich.unity.auth.models.UserAuthentication;
import com.github.vlsidlyarevich.unity.auth.security.AuthenticationFacade;
import com.github.vlsidlyarevich.unity.auth.service.CurrentUserService;
import com.github.vlsidlyarevich.unity.db.domain.Authority;
import com.github.vlsidlyarevich.unity.db.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SecurityContextCurrentUserService implements CurrentUserService {

    private final AuthenticationFacade authenticationFacade;

    @Autowired
    public SecurityContextCurrentUserService(final AuthenticationFacade authenticationFacade) {
        this.authenticationFacade = authenticationFacade;
    }

    @Override
    public boolean canAccessUser(final String userId) {
        UserAuthentication currentUser
                = (UserAuthentication) authenticationFacade.getAuthentication();
        return currentUser != null
                && (currentUser.getAuthorities().contains(Authority.ROLE_ADMIN)
                || ((User) currentUser.getDetails()).getId().equals(userId));
    }
}
