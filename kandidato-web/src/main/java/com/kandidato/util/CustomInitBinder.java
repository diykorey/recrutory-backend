package com.kandidato.util;

import com.kandidato.constants.VacancyState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

/**
 * Created by diyko on 07.04.2014.
 */
@ControllerAdvice
public class CustomInitBinder {
    private static final Logger log = LoggerFactory.getLogger(CustomInitBinder.class);

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        System.out.println("INIT BINDER");
        binder.registerCustomEditor(VacancyState.class, new VacancyStateEnumConverter());
    }

}
