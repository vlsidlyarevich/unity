package com.github.vlsidlyarevich.unity.web.security.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public interface TokenAuthenticationService {

    Authentication authenticate(HttpServletRequest request);

    void addAuthentication(HttpServletResponse response, UserDetails userDetails);
}
