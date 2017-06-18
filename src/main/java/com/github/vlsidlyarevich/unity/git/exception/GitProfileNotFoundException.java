package com.github.vlsidlyarevich.unity.git.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

@Data
public class GitProfileNotFoundException extends HttpClientErrorException {

    private String key;
    private Object[] args;

    public GitProfileNotFoundException(final HttpStatus statusCode, final String key) {
        super(statusCode);
        this.key = key;
    }

    public GitProfileNotFoundException(final HttpStatus statusCode,
                                       final String key, final Object[] args) {
        super(statusCode);
        this.key = key;
        this.args = args;
    }
}
