package com.github.vlsidlyarevich.unity.web.security.service;


public interface TokenService {

    String getToken(String username, String password);
}
