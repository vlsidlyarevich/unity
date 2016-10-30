package com.github.vlsidlyarevich.unity.exception;

import java.io.FileNotFoundException;

/**
 * Created by vladislav on 10/30/16.
 */
public class FileSystemFileNotFoundException extends FileNotFoundException {

    private static final long serialVersionUID = 3194206510987651825L;

    private String key;
    private Object[] args;

    public FileSystemFileNotFoundException(String key, Object[] args) {
        super();
        this.key = key;
        this.args = args;
    }

    public FileSystemFileNotFoundException(String message, String key) {
        super(message);
        this.key = key;
    }

    public FileSystemFileNotFoundException(String message, String key, Object[] args) {
        super(message);
        this.key = key;
        this.args = args;
    }
}
