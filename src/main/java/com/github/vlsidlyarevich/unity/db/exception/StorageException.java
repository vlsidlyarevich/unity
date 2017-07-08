package com.github.vlsidlyarevich.unity.db.exception;


class StorageException extends RuntimeException {

    private static final long serialVersionUID = -8837336635325252439L;

    StorageException() {
        super();
    }

    StorageException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
