package com.github.vlsidlyarevich.unity.web.exception.handler;

import com.github.vlsidlyarevich.unity.web.dto.exception.ExceptionResponse;
import com.github.vlsidlyarevich.unity.web.security.exception.BadCredentialsException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@ControllerAdvice
public class SecurityExceptionHandler {

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity handleBadCredentialsException(final BadCredentialsException exception,
                                                        final HttpServletRequest req) {
        log.warn("Processing bad credentials exception: {}", exception.getMessage());

        return new ResponseEntity<>(new ExceptionResponse(exception.getLocalizedMessage()),
                HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity handleAccessDeniedException(final AccessDeniedException exception,
                                                      final HttpServletRequest req) {
        log.warn("Processing access denied exception: {}", exception.getMessage());

        return new ResponseEntity<>(new ExceptionResponse(exception.getLocalizedMessage()),
                HttpStatus.UNAUTHORIZED);
    }
}
