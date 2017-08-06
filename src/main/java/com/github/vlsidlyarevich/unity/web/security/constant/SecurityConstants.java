package com.github.vlsidlyarevich.unity.web.security.constant;


public final class SecurityConstants {

    public static final String AUTH_HEADER_NAME = "x-auth-token";
    public static final String PASSWORD_REGEXP = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{5,}$";

    private SecurityConstants() {

    }
}
