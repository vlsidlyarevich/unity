package com.github.vlsidlyarevich.unity.web.security.service;

import com.github.vlsidlyarevich.unity.web.security.model.UserAuthentication;
import com.github.vlsidlyarevich.unity.web.security.facade.AuthenticationFacade;
import com.github.vlsidlyarevich.unity.web.security.model.Authority;
import com.github.vlsidlyarevich.unity.domain.model.User;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class SecurityContextCurrentUserService implements CurrentUserService {

    private final AuthenticationFacade authenticationFacade;

    @Override
    public boolean canAccessUserOrAdmin(final String userId) {
        UserAuthentication currentUser
                = (UserAuthentication) authenticationFacade.getAuthentication();
        return currentUser != null
                && (currentUser.getAuthorities().contains(Authority.ROLE_ADMIN)
                || ((User) currentUser.getDetails()).getId().equals(userId));
    }
}
