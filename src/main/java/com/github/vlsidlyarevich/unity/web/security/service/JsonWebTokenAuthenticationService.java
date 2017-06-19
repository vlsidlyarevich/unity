package com.github.vlsidlyarevich.unity.web.security.service;

import com.github.vlsidlyarevich.unity.web.security.constant.SecurityConstants;
import com.github.vlsidlyarevich.unity.web.security.model.UserAuthentication;
import com.github.vlsidlyarevich.unity.common.exception.UserNotFoundException;
import com.github.vlsidlyarevich.unity.db.domain.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Service
public class JsonWebTokenAuthenticationService implements TokenAuthenticationService {

    @Value("security.token.secret.key")
    private String secretKey;

    private final TokenService tokenService;

    private final UserDetailsService userDetailsService;

    @Autowired
    public JsonWebTokenAuthenticationService(final TokenService tokenService,
                                             final UserDetailsService userDetailsService) {
        this.tokenService = tokenService;
        this.userDetailsService = userDetailsService;
    }

    @Override
    public Authentication authenticate(final HttpServletRequest request) {
        String token = request.getHeader(SecurityConstants.AUTH_HEADER_NAME);
        Jws<Claims> tokenData = parseToken(token);
        if (tokenData != null) {
            try {
                User user = getUserFromToken(tokenData);
                if (user != null) {
                    return new UserAuthentication(user);
                }
            } catch (UserNotFoundException e) {
                log.warn(e.getMessage());
            }
        }
        return null;
    }

    @Override
    public void addAuthentication(final HttpServletResponse response,
                                  final UserDetails userDetails) {
        final User user = (User) userDetails;
        response.addHeader(SecurityConstants.AUTH_HEADER_NAME,
                tokenService.getToken(user.getUsername(), user.getPassword()));
    }

    private Jws<Claims> parseToken(final String token) {
        if (token != null) {
            try {
                return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException
                    | SignatureException | IllegalArgumentException e) {
                return null;
            }
        }
        return null;
    }

    private User getUserFromToken(final Jws<Claims> tokenData)
            throws UserNotFoundException {
        try {
            return (User) userDetailsService.loadUserByUsername(tokenData.getBody()
                    .get("username").toString());
        } catch (UsernameNotFoundException e) {
            throw new UserNotFoundException("User " + tokenData.getBody()
                    .get("username").toString() + " not found");
        }
    }
}
