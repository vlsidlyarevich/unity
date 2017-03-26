package com.github.vlsidlyarevich.unity.git.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

@Data
public class GitRepositoryNotFoundException extends HttpClientErrorException {

    private String key;
    private Object[] args;

    public GitRepositoryNotFoundException(HttpStatus statusCode, String key) {
        super(statusCode);
        this.key = key;
    }

    public GitRepositoryNotFoundException(HttpStatus statusCode, String key, Object[] args) {
        super(statusCode);
        this.key = key;
        this.args = args;
    }
}
