package com.kandidato.util;

import com.kandidato.constants.CommentType;

import java.beans.PropertyEditorSupport;

/**
 * Case-insensitive converter, from {@link java.lang.String} to {@link CommentType}.
 *
 * Created by Andriy Andrunevchyn on 4/26/14.
 */
public class CommentTypeEnumConverter extends PropertyEditorSupport {

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if (text != null)
            setValue(CommentType.valueOf(text.trim().toUpperCase()));
    }
}
