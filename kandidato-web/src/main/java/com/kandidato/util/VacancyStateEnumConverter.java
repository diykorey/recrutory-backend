package com.kandidato.util;

import com.kandidato.constants.VacancyState;

import java.beans.PropertyEditorSupport;

/**
 * Created by diyko on 07.04.2014.
 */
public class VacancyStateEnumConverter extends PropertyEditorSupport {
    @Override
    public void setAsText(final String text) throws IllegalArgumentException {
        if (text != null)
            setValue(VacancyState.valueOf(text.trim().toUpperCase()));
    }
}