package com.github.vlsidlyarevich.unity.web.exception.handler;

import com.github.vlsidlyarevich.unity.web.dto.exception.ExceptionResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@ControllerAdvice
public class AbstractExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity handleAbstractException(final Exception exception,
                                                  final HttpServletRequest req) {
        log.warn("Processing abstract exception:" + exception.getMessage());

        return new ResponseEntity<>(new ExceptionResponse(exception.getLocalizedMessage()),
                HttpStatus.BAD_REQUEST);
    }
}
