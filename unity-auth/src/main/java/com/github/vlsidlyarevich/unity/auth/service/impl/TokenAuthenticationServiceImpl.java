package com.github.vlsidlyarevich.unity.auth.service.impl;

import com.github.vlsidlyarevich.unity.auth.service.TokenAuthenticationService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class TokenAuthenticationServiceImpl implements TokenAuthenticationService {

    @Value("security.token.secret.key")
    private String secretKey;


}
