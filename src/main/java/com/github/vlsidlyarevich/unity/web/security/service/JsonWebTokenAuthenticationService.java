package com.github.vlsidlyarevich.unity.web.security.service;

import com.github.vlsidlyarevich.unity.common.exception.UserNotFoundException;
import com.github.vlsidlyarevich.unity.db.domain.User;
import com.github.vlsidlyarevich.unity.web.security.constant.SecurityConstants;
import com.github.vlsidlyarevich.unity.web.security.model.UserAuthentication;
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
import java.util.Optional;

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
    public Optional<Authentication> authenticate(final HttpServletRequest request) {
        final String token = request.getHeader(SecurityConstants.AUTH_HEADER_NAME);
        final Optional<Jws<Claims>> tokenData = parseToken(token);
        if (tokenData.isPresent()) {
            try {
                Optional<User> user = getUserFromToken(tokenData.get());
                if (user.isPresent()) {
                    return Optional.of(new UserAuthentication(user.get()));
                }
            } catch (UserNotFoundException e) {
                log.warn(e.getMessage());
            }
        }
        return Optional.empty();
    }

    @Override
    public void addAuthentication(final HttpServletResponse response,
                                  final UserDetails userDetails) {
        final User user = (User) userDetails;
        response.addHeader(SecurityConstants.AUTH_HEADER_NAME,
                tokenService.getToken(user.getUsername(), user.getPassword()).get());
    }

    private Optional<Jws<Claims>> parseToken(final String token) {
        Optional<Jws<Claims>> result = Optional.empty();
        if (token != null) {
            try {
                result = Optional.ofNullable(Jwts.parser()
                        .setSigningKey(secretKey).parseClaimsJws(token));
            } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException
                    | SignatureException | IllegalArgumentException e) {
                return result;
            }
        }
        return result;
    }

    private Optional<User> getUserFromToken(final Jws<Claims> tokenData)
            throws UserNotFoundException {
        try {
            return Optional.ofNullable((User) userDetailsService
                    .loadUserByUsername(tokenData.getBody()
                    .get("username").toString()));
        } catch (UsernameNotFoundException e) {
            throw new UserNotFoundException("User " + tokenData.getBody()
                    .get("username").toString() + " not found");
        }
    }
}