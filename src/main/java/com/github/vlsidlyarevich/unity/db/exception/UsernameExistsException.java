package com.github.vlsidlyarevich.unity.db.exception;


public class UsernameExistsException extends RuntimeException {

    private static final long serialVersionUID = -4437240414033651438L;

    public UsernameExistsException(final String message) {
        super(message);
    }
}
