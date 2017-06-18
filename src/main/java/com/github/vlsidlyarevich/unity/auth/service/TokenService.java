package com.github.vlsidlyarevich.unity.auth.service;


public interface TokenService {

    String getToken(String username, String password);
}
