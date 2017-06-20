package com.github.vlsidlyarevich.unity.web.security.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;


public interface TokenAuthenticationService {

    Optional<Authentication> authenticate(HttpServletRequest request);

    void addAuthentication(HttpServletResponse response, UserDetails userDetails);
}
