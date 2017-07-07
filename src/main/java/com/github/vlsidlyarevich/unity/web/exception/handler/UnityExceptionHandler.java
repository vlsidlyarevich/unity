package com.github.vlsidlyarevich.unity.web.exception.handler;

import com.github.vlsidlyarevich.unity.common.exception.UserNotFoundException;
import com.github.vlsidlyarevich.unity.db.exception.FileSystemFileNotFoundException;
import com.github.vlsidlyarevich.unity.db.exception.FileSystemStorageException;
import com.github.vlsidlyarevich.unity.db.exception.UsernameExistsException;
import com.github.vlsidlyarevich.unity.i18n.MessageResolver;
import com.github.vlsidlyarevich.unity.web.dto.ExceptionDTO;
import com.github.vlsidlyarevich.unity.web.security.exception.BadCredentialsException;
import com.mongodb.MongoException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
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

    private final MessageResolver messageResolver;

    @Autowired
    public UnityExceptionHandler(final MessageResolver messageResolver) {
        this.messageResolver = messageResolver;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleMethodArgumentNotValidException(
            final MethodArgumentNotValidException exception,
            final HttpServletRequest req) {
        log.warn("Processing method argument not valid exception: " + exception.getMessage());
        final String message = exception.getBindingResult().getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining("\n"));

        return new ResponseEntity<>(new ExceptionDTO(message), HttpStatus.BAD_REQUEST);
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

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity handleBadCredentialsException(final BadCredentialsException exception,
                                                        final HttpServletRequest req) {
        log.warn("Processing bad credentials exception:" + exception.getMessage());

        return new ResponseEntity<>(new ExceptionDTO(exception.getLocalizedMessage()),
                HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(MongoException.class)
    public ResponseEntity handleMongoException(final MongoException exception,
                                               final HttpServletRequest req) {
        log.warn("Processing mongo exception:" + exception.getMessage());

        return new ResponseEntity<>(new ExceptionDTO(exception.getLocalizedMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity handleAccessDeniedException(final AccessDeniedException exception,
                                                      final HttpServletRequest req) {
        log.warn("Processing access denied exception:" + exception.getMessage());

        return new ResponseEntity<>(new ExceptionDTO(exception.getLocalizedMessage()),
                HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity handleAbstractException(final Exception exception,
                                                  final HttpServletRequest req) {
        log.warn("Processing abstract exception:" + exception.getMessage());

        return new ResponseEntity<>(new ExceptionDTO(exception.getLocalizedMessage()),
                HttpStatus.BAD_REQUEST);
    }
}
