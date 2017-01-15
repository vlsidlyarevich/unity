package com.github.vlsidlyarevich.unity.web.security.service.impl;

import com.github.vlsidlyarevich.unity.web.security.service.TokenService;
import com.github.vlsidlyarevich.unity.web.security.service.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
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
