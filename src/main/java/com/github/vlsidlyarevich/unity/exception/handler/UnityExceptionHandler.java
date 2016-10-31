package com.github.vlsidlyarevich.unity.exception.handler;

import com.github.vlsidlyarevich.unity.config.MessageResolver;
import com.github.vlsidlyarevich.unity.exception.FileSystemFileNotFoundException;
import com.github.vlsidlyarevich.unity.exception.FileSystemStorageException;
import com.github.vlsidlyarevich.unity.exception.model.ExceptionModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by vladislav on 10/29/16.
 */
@Slf4j
@ControllerAdvice
public class UnityExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageResolver messageResolver;

    @ExceptionHandler(FileSystemStorageException.class)
    public ResponseEntity fileSystemStorageExceptionHandler(FileSystemStorageException exception, HttpServletRequest req) {
        log.warn("Processing file system storage exception:" + exception.getMessage());

        return new ResponseEntity<>(new ExceptionModel(messageResolver.getMessage(exception.getKey(),
                exception.getArgs())), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FileSystemFileNotFoundException.class)
    public ResponseEntity fileSystemFileNotFoundExceptionHandler(FileSystemFileNotFoundException exception, HttpServletRequest req) {
        log.warn("Processing file system file not found exception:" + exception.getMessage());

        return new ResponseEntity<>("http://www.placehold.it/200x150/EFEFEF/AAAAAA&amp;text=no+image", HttpStatus.OK);
    }

}
