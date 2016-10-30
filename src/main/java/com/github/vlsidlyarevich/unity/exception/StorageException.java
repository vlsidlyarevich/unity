package com.github.vlsidlyarevich.unity.exception;

/**
 * Created by vladislav on 10/29/16.
 */
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
