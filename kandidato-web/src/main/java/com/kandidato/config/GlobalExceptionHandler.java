package com.kandidato.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.annotation.PostConstruct;
import javax.servlet.ServletException;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @PostConstruct
    public void init() {
        log.info("Global exception handler created");
    }


    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public String handleException(MissingServletRequestParameterException ex) {
        log.error("Missing parameter:{}", ex.toString());
        log.debug("Stacktrace: ", ex);
        return "Missing mandatory parameter - " + ex.getMessage();
    }


    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public String handleException(ServletException ex) {
        log.error("Error happened: {}", ex);
        log.debug("Stacktrace: ", ex);
        return "An error occurred please try again later. ";
    }

    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public String handleException(RuntimeException ex) {
        log.error("RuntimeException happened", ex);
        log.debug("Stacktrace: ", ex);
        return "An error occurred please try again later. ";
    }

    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public String handleException(Exception ex) {
        log.error("Exception happened: ", ex);
        log.debug("Stacktrace: ", ex);
        return "An error occurred please try again later. ";
    }

}
