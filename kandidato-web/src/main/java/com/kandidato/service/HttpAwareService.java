package com.kandidato.service;

import com.kandidato.exception.ResourceNotFoundException;
import com.kandidato.model.dto.error.ErrorReport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Class which provides mapping from basic java exceptions to HTTP Response codes.
 * <p/>
 * Created by andriy on 4/26/14.
 */
public abstract class HttpAwareService {

    private static final Logger log = LoggerFactory.getLogger(HttpAwareService.class);

    @Autowired
    private MessageSource messageSource;

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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorReport handleException(MethodArgumentNotValidException e) {
        BindingResult result = e.getBindingResult();
        List<org.springframework.validation.FieldError> fieldErrors = result.getFieldErrors();
        List<com.kandidato.model.dto.error.FieldError> errors = new ArrayList<>(fieldErrors.size());
        for (FieldError fieldError : fieldErrors) {
            errors.add(new com.kandidato.model.dto.error.FieldError(fieldError.getField(), resolveLocalizedErrorMessage(fieldError)));

        }
        return new ErrorReport(errors);
    }

    private String resolveLocalizedErrorMessage(FieldError fieldError) {
        Locale currentLocale = LocaleContextHolder.getLocale();
        String localizedErrorMessage = messageSource.getMessage(fieldError, currentLocale);
        return localizedErrorMessage;
    }
}
