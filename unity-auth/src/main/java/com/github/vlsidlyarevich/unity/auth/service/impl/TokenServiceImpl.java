package com.github.vlsidlyarevich.unity.auth.service.impl;

import com.github.vlsidlyarevich.unity.auth.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;


@Component
public class TokenServiceImpl implements TokenService {

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public String getToken(String username, String password) {
        return null;
    }
}
