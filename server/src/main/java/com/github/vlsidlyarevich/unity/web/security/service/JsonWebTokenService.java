package com.github.vlsidlyarevich.unity.web.security.service;

import com.github.vlsidlyarevich.unity.domain.model.User;
import com.github.vlsidlyarevich.unity.domain.exception.UserNotFoundException;
import com.github.vlsidlyarevich.unity.web.security.exception.BadCredentialsException;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class JsonWebTokenService implements TokenService {

    private static final int TOKEN_EXPIRATION_TIME = 30;

    @Value("${security.token.secret.key}")
    private String tokenKey;

    private final UserDetailsService userDetailsService;

    @Override
    //FIXME refactor this to use Authentication and rememberMe option
    public String getToken(final String username, final String password) {
        try {
            return Optional.ofNullable(userDetailsService.loadUserByUsername(username))
                    .filter(userDetails -> password.equals(userDetails.getPassword()))
                    .map(userDetails -> createToken(new HashMap<>(), (User) userDetails))
                    .orElseThrow(() -> new BadCredentialsException(
                            "Authentication Error, credentials not matching"));

        } catch (UserNotFoundException e) {
            throw new BadCredentialsException("Authentication Error, credentials not matching");
        }
    }

    private String createToken(final Map<String, Object> tokenData, final User user) {
        fulfillTokenUserData(tokenData, user);
        fulfillTokenDates(tokenData);

        final JwtBuilder jwtBuilder = Jwts.builder();
        prepareJwtBuilder(jwtBuilder);
        jwtBuilder.setClaims(tokenData);

        return jwtBuilder.signWith(SignatureAlgorithm.HS512, tokenKey).compact();
    }

    private void fulfillTokenUserData(final Map<String, Object> tokenData, final User user) {
        tokenData.put("clientType", "user");
        tokenData.put("userID", user.getId());
        tokenData.put("username", user.getUsername());
    }

    private void fulfillTokenDates(final Map<String, Object> tokenData) {
        final Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, TOKEN_EXPIRATION_TIME);
        tokenData.put("token_expiration_date", calendar.getTime());
        tokenData.put("token_create_date", LocalDateTime.now());
    }

    private void prepareJwtBuilder(final JwtBuilder jwtBuilder) {
        final Calendar calendar = Calendar.getInstance();
        jwtBuilder.setExpiration(calendar.getTime());
    }
}
