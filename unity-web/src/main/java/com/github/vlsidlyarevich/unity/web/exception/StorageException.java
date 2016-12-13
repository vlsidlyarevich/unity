package com.github.vlsidlyarevich.unity.web.exception;


public class StorageException extends RuntimeException {

    private static final long serialVersionUID = -8837336635325252439L;

    public StorageException() {
        super();
    }

    public StorageException(String message) {
        super(message);
    }

    public StorageException(String message, Throwable cause) {
        super(message, cause);
    }
}
