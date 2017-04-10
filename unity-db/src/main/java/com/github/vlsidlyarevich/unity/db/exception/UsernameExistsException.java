package com.github.vlsidlyarevich.unity.db.exception;


public class UsernameExistsException extends RuntimeException {

    private static final long serialVersionUID = -4437240414033651438L;

    public UsernameExistsException() {
        super();
    }

    public UsernameExistsException(String message) {
        super(message);
    }

    public UsernameExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
