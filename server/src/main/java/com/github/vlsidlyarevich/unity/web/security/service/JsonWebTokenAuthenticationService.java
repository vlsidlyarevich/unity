package com.github.vlsidlyarevich.unity.web.security.service;

import com.github.vlsidlyarevich.unity.domain.exception.UserNotFoundException;
import com.github.vlsidlyarevich.unity.domain.model.User;
import com.github.vlsidlyarevich.unity.web.security.model.UserAuthentication;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class JsonWebTokenAuthenticationService implements TokenAuthenticationService {

    @Value("${security.token.secret.key}")
    private String secretKey;

    @Value("${security.token.header.name}")
    private String authHeaderName;

    private final UserDetailsService userDetailsService;

    @Override
    public Authentication authenticate(final HttpServletRequest request) {
        final String token = request.getHeader(authHeaderName);
        final Optional<Jws<Claims>> tokenData = parseToken(token);

        if (tokenData.isPresent()) {
            try {
                final User user = getUserFromToken(tokenData.get());

                return new UserAuthentication(user);
            } catch (UserNotFoundException e) {
                log.warn(e.getMessage());
            }
        }

        return null;
    }

    private Optional<Jws<Claims>> parseToken(final String token) {
        Optional<Jws<Claims>> result = Optional.empty();

        if (Objects.nonNull(token)) {
            try {
                result = Optional.ofNullable(Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token));
            } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException
                    | SignatureException | IllegalArgumentException e) {
                log.warn(e.getMessage());
            }
        }

        return result;
    }

    private User getUserFromToken(final Jws<Claims> tokenData) {
        final String username = tokenData.getBody().get("username").toString();

        return Optional.ofNullable((User) userDetailsService.loadUserByUsername(username))
                .orElseThrow(() -> new UserNotFoundException(String.format("User %s not found", username)));
    }
}
