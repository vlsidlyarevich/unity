package com.github.vlsidlyarevich.unity.web.exception.handler;

import com.github.vlsidlyarevich.unity.auth.exception.UserNotFoundException;
import com.github.vlsidlyarevich.unity.db.exception.FileSystemFileNotFoundException;
import com.github.vlsidlyarevich.unity.db.exception.FileSystemStorageException;
import com.github.vlsidlyarevich.unity.db.exception.UsernameExistsException;
import com.github.vlsidlyarevich.unity.web.config.MessageResolver;
import com.github.vlsidlyarevich.unity.web.exception.model.ExceptionModel;
import com.mongodb.MongoException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
public class UnityExceptionHandler {

    @Autowired
    private MessageResolver messageResolver;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleMethodArgumentNotValidException(MethodArgumentNotValidException exception, HttpServletRequest req) {
        log.warn("Processing method argument not valid exception: " + exception.getMessage());
        String message = exception.getBindingResult().getFieldErrors()
                .stream()
                .map(fieldError -> fieldError.getDefaultMessage())
                .collect(Collectors.joining("\n"));

        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FileSystemStorageException.class)
    public ResponseEntity handleFileSystemStorageException(FileSystemStorageException exception, HttpServletRequest req) {
        log.warn("Processing file system storage exception:" + exception.getMessage());

        return new ResponseEntity<>(new ExceptionModel(messageResolver.getMessage(exception.getKey(),
                exception.getArgs())), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FileSystemFileNotFoundException.class)
    public ResponseEntity handlefileSystemFileNotFoundException(FileSystemFileNotFoundException exception, HttpServletRequest req) {
        log.warn("Processing file system file not found exception:" + exception.getMessage());

        return new ResponseEntity<>(new ExceptionModel(messageResolver.getMessage(exception.getKey(),
                exception.getArgs())), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity handleUserNotFoundException(UserNotFoundException exception, HttpServletRequest req) {
        log.warn("Processing user not found exception:" + exception.getMessage());

        return new ResponseEntity<>(exception.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UsernameExistsException.class)
    public ResponseEntity handleUsernameExistsException(UsernameExistsException exception, HttpServletRequest req) {
        log.warn("Processing user with such username exists exception:" + exception.getMessage());

        return new ResponseEntity<>(exception.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MongoException.class)
    public ResponseEntity handleMongoException(MongoException exception, HttpServletRequest req) {
        log.warn("Processing mongo exception:" + exception.getMessage());

        return new ResponseEntity<>(exception.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity handleAccessDeniedException(AccessDeniedException exception, HttpServletRequest req) {
        log.warn("Processing access denied exception:" + exception.getMessage());

        return new ResponseEntity<>(exception.getLocalizedMessage(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity handleAbstractException(Exception exception, HttpServletRequest req) {
        log.warn("Processing abstract exception:" + exception.getMessage());

        return new ResponseEntity<>(exception.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
    }
}
