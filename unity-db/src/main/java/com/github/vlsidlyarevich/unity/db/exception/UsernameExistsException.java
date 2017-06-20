package com.github.vlsidlyarevich.unity.db.exception;


public class UsernameExistsException extends RuntimeException {

    private static final long serialVersionUID = -4437240414033651438L;

    public UsernameExistsException() {
        super();
    }

    public UsernameExistsException(final String message) {
        super(message);
    }

    public UsernameExistsException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
