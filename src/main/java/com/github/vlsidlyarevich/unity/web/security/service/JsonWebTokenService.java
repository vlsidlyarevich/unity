package com.github.vlsidlyarevich.unity.web.security.service;

import com.github.vlsidlyarevich.unity.db.domain.User;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
public class JsonWebTokenService implements TokenService {

    private static final int TOKEN_EXPIRATION_TIME = 30;

    @Value("security.token.secret.key")
    private String tokenKey;

    private final UserDetailsService userDetailsService;

    @Autowired
    public JsonWebTokenService(final UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    public Optional<String> getToken(final String username, final String password) {
        Optional<String> result = Optional.empty();

        if (username == null || password == null) {
            return result;
        }

        final User user = (User) userDetailsService.loadUserByUsername(username);
        final Map<String, Object> tokenData = new HashMap<>();
        if (password.equals(user.getPassword())) {
            tokenData.put("clientType", "user");
            tokenData.put("userID", user.getId());
            tokenData.put("username", user.getUsername());
            tokenData.put("token_create_date", LocalDateTime.now());
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.MINUTE, TOKEN_EXPIRATION_TIME);
            tokenData.put("token_expiration_date", calendar.getTime());
            final JwtBuilder jwtBuilder = Jwts.builder();
            jwtBuilder.setExpiration(calendar.getTime());
            jwtBuilder.setClaims(tokenData);
            result = Optional.of(jwtBuilder.signWith(SignatureAlgorithm.HS512, tokenKey).compact());
        } else {
            log.warn("Authentication Error, credentials not matching");
        }

        return result;
    }
}
