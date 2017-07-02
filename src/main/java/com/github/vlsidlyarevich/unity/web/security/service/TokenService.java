package com.github.vlsidlyarevich.unity.web.security.service;

import java.util.Optional;

public interface TokenService {

    Optional<String> getToken(String username, String password);
}
