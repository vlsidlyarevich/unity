package com.github.vlsidlyarevich.unity.web.security.exception;

public class BadCredentialsException extends RuntimeException {

    private static final long serialVersionUID = 9024154940038061577L;

    public BadCredentialsException(final String message) {
        super(message);
    }
}
