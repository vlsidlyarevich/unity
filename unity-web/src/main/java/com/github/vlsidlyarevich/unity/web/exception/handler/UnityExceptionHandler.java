package com.github.vlsidlyarevich.unity.web.exception.handler;

import com.github.vlsidlyarevich.unity.web.config.MessageResolver;
import com.github.vlsidlyarevich.unity.db.exception.FileSystemFileNotFoundException;
import com.github.vlsidlyarevich.unity.db.exception.FileSystemStorageException;
import com.github.vlsidlyarevich.unity.web.exception.model.ExceptionModel;
import com.mongodb.MongoException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;


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

        return new ResponseEntity<>(new ExceptionModel(messageResolver.getMessage(exception.getKey(),
                exception.getArgs())), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MongoException.class)
    public ResponseEntity handleMongoException(MongoException exception, HttpServletRequest req) {
        log.warn("Processing mongo exception:" + exception.getMessage());

        return new ResponseEntity<>(exception.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity handleAbstractException(Exception exception, HttpServletRequest req) {
        log.warn("Processing abstract exception:" + exception.getMessage());

        return new ResponseEntity<>(exception.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
    }
}
