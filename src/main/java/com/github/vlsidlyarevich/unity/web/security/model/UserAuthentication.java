package com.github.vlsidlyarevich.unity.web.security.model;

import com.github.vlsidlyarevich.unity.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@RequiredArgsConstructor
public class UserAuthentication implements Authentication {

    private static final long serialVersionUID = 8464592409085068967L;

    private final User user;
    private boolean authenticated = true;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return user.getAuthorities();
    }

    @Override
    public Object getCredentials() {
        return user.getPassword();
    }

    @Override
    public Object getDetails() {
        return user;
    }

    @Override
    public Object getPrincipal() {
        return user.getUsername();
    }

    @Override
    public boolean isAuthenticated() {
        return authenticated;
    }

    @Override
    public void setAuthenticated(final boolean authenticated) {
        this.authenticated = authenticated;
    }

    @Override
    public String getName() {
        return user.getUsername();
    }
}
