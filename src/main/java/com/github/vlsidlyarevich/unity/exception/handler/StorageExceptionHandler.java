package com.github.vlsidlyarevich.unity.exception.handler;

import com.github.vlsidlyarevich.unity.exception.FileSystemFileNotFoundException;
import com.github.vlsidlyarevich.unity.exception.FileSystemStorageException;
import com.github.vlsidlyarevich.unity.exception.model.ExceptionModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.text.MessageFormat;
import java.util.ResourceBundle;

/**
 * Created by vladislav on 10/29/16.
 */
@Slf4j
@ControllerAdvice
public class StorageExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private ResourceBundle resourceBundle;

    @ExceptionHandler(FileSystemStorageException.class)
    public ResponseEntity fileSystemStorageExceptionHandler(FileSystemStorageException exception, WebRequest webRequest) {
        log.warn("Handling exception: " + resourceBundle.getString(exception.getMessage()) + "\n With cause: "
                + exception.getCause());

        String pattern = resourceBundle.getString(exception.getKey());

        return new ResponseEntity<>(new ExceptionModel(MessageFormat.format(pattern, exception.getArgs())), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FileSystemFileNotFoundException.class)
    public ResponseEntity fileSystemFileNotFoundExceptionHandler(FileSystemFileNotFoundException exception, WebRequest webRequest) {
        log.warn("Handling exception: " + resourceBundle.getString(exception.getMessage()) + "\n With cause: "
                + exception.getCause());

        return new ResponseEntity<>("http://www.placehold.it/200x150/EFEFEF/AAAAAA&amp;text=no+image", HttpStatus.OK);
    }

}
