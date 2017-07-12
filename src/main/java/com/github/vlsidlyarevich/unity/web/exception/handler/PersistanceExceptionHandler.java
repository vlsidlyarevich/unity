package com.github.vlsidlyarevich.unity.web.exception.handler;

import com.github.vlsidlyarevich.unity.db.exception.*;
import com.github.vlsidlyarevich.unity.i18n.MessageResolver;
import com.github.vlsidlyarevich.unity.web.dto.ExceptionDTO;
import com.mongodb.MongoException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@ControllerAdvice
public class PersistanceExceptionHandler {


    private final MessageResolver messageResolver;

    @Autowired
    public PersistanceExceptionHandler(final MessageResolver messageResolver) {
        this.messageResolver = messageResolver;
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity handleResourceNotFoundException(
            final ResourceNotFoundException exception,
            final HttpServletRequest req) {
        log.warn("Processing resource not found exception:" + exception.getMessage());

        return new ResponseEntity<>(new ExceptionDTO(exception.getLocalizedMessage()),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(FileSystemStorageException.class)
    public ResponseEntity handleFileSystemStorageException(
            final FileSystemStorageException exception,
            final HttpServletRequest req) {
        log.warn("Processing file system storage exception:" + exception.getMessage());

        return new ResponseEntity<>(new ExceptionDTO(messageResolver.getMessage(exception.getKey(),
                exception.getArgs())), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(FileSystemFileNotFoundException.class)
    public ResponseEntity handlefileSystemFileNotFoundException(
            final FileSystemFileNotFoundException exception,
            final HttpServletRequest req) {
        log.warn("Processing file system file not found exception:" + exception.getMessage());

        return new ResponseEntity<>(new ExceptionDTO(messageResolver.getMessage(exception.getKey(),
                exception.getArgs())), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity handleUserNotFoundException(final UserNotFoundException exception,
                                                      final HttpServletRequest req) {
        log.warn("Processing user not found exception:" + exception.getMessage());

        return new ResponseEntity<>(new ExceptionDTO(exception.getLocalizedMessage()),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UsernameExistsException.class)
    public ResponseEntity handleUsernameExistsException(final UsernameExistsException exception,
                                                        final HttpServletRequest req) {
        log.warn("Processing user with such username exists exception:" + exception.getMessage());

        return new ResponseEntity<>(new ExceptionDTO(exception.getLocalizedMessage()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MongoException.class)
    public ResponseEntity handleMongoException(final MongoException exception,
                                               final HttpServletRequest req) {
        log.warn("Processing mongo exception:" + exception.getMessage());

        return new ResponseEntity<>(new ExceptionDTO(exception.getLocalizedMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
