package com.github.vlsidlyarevich.unity.db.exception;


public class ServiceException extends RuntimeException {

    private static final long serialVersionUID = -8658131859261427602L;

    private String service;

    public ServiceException(final String service) {
        super();
        this.service = service;
    }

    public ServiceException(final String message, final String service) {
        super(message);
        this.service = service;
    }

    public ServiceException(final String message, final Throwable cause,
                            final String service) {
        super(message, cause);
        this.service = service;
    }
}
