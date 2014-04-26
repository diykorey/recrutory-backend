package com.kandidato.service;

import com.kandidato.exception.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Class which provides mapping from basic java exceptions to HTTP Response codes.
 * <p/>
 * Created by andriy on 4/26/14.
 */
public abstract class HttpAwareService {

    private static final Logger log = LoggerFactory.getLogger(HttpAwareService.class);

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public void handleException(ResourceNotFoundException e) {
        log.debug("Resource not found {}", e);
    }


    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(value = HttpStatus.PRECONDITION_FAILED)
    public void handleException(IllegalArgumentException e) {
        log.debug("Invalid arguments {}", e);
    }
}
