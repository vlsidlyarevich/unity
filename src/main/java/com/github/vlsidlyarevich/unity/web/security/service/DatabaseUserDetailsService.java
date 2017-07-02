package com.github.vlsidlyarevich.unity.web.security.service;

import com.github.vlsidlyarevich.unity.db.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DatabaseUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Autowired
    public DatabaseUserDetailsService(final UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(final String username)
            throws UsernameNotFoundException {
        return Optional.ofNullable(userService.findByUsername(username))
                .orElseThrow(() ->
                        new UsernameNotFoundException(String
                                .format("User with username:%s not found", username)));
    }
}
