package com.github.vlsidlyarevich.unity.db.exception;


public class ServiceException extends RuntimeException {

    private static final long serialVersionUID = -8658131859261427602L;

    private String service;

    public ServiceException(String service) {
        super();
        this.service = service;
    }

    public ServiceException(String message, String service) {
        super(message);
        this.service = service;
    }

    public ServiceException(String message, Throwable cause, String service) {
        super(message, cause);
        this.service = service;
    }
}
