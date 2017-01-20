package com.github.vlsidlyarevich.unity.auth.service.impl;

import com.github.vlsidlyarevich.unity.auth.service.TokenAuthenticationService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.token.TokenService;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;


@Service
public class TokenAuthenticationServiceImpl implements TokenAuthenticationService {

    @Value("security.token.secret.key")
    private String secretKey;

    @Autowired
    private TokenService tokenService;

    @Override
    public Authentication authenticate(HttpServletRequest request) {
        final Jws<Claims> tokenData = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(request.getHeader(secretKey));
        tokenData.getBody().getSubject()

    }

    private User getUserFromToken(Jws<Claims> tokenData) throws UserNot
}
