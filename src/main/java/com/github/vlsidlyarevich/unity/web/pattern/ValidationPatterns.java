package com.github.vlsidlyarevich.unity.web.pattern;

public final class ValidationPatterns {

    public static final String USER_PASSWORD_PATTERN
            = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{5,}$";

    private ValidationPatterns() {

    }
}
