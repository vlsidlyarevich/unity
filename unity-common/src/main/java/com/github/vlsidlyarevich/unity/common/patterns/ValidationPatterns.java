package com.github.vlsidlyarevich.unity.common.patterns;

public final class ValidationPatterns {

    private static String USER_PASSWORD_PATTERN
            = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{5,}$";

    private ValidationPatterns() {

    }

    public static String getUserPasswordPattern() {
        return USER_PASSWORD_PATTERN;
    }
}
