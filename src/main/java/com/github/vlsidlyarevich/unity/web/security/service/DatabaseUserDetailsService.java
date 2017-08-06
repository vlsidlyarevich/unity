package com.github.vlsidlyarevich.unity.web.security.service;

import com.github.vlsidlyarevich.unity.db.exception.UserNotFoundException;
import com.github.vlsidlyarevich.unity.db.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class DatabaseUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(final String username) {
        return Optional.ofNullable(userService.findByUsername(username))
                .orElseThrow(() ->
                        new UserNotFoundException(String
                                .format("User with username:%s not found", username)));
    }
}
