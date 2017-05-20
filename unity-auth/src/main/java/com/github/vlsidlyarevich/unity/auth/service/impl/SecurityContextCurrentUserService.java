package com.github.vlsidlyarevich.unity.auth.service.impl;

import com.github.vlsidlyarevich.unity.auth.models.UserAuthentication;
import com.github.vlsidlyarevich.unity.auth.service.CurrentUserService;
import com.github.vlsidlyarevich.unity.db.domain.Authority;
import com.github.vlsidlyarevich.unity.db.domain.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class SecurityContextCurrentUserService implements CurrentUserService {

    @Override
    public boolean canAccessUser(final String userId) {
        UserAuthentication currentUser
                = (UserAuthentication) SecurityContextHolder.getContext().getAuthentication();
        return currentUser != null
                && (currentUser.getAuthorities().contains(Authority.ROLE_ADMIN)
                || ((User) currentUser.getDetails()).getId().equals(userId));
    }
}
