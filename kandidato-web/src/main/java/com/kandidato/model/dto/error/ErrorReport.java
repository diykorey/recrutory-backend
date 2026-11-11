package com.kandidato.model.dto.error;

import com.kandidato.model.dto.Dto;

import java.util.ArrayList;
import java.util.List;

/**
 * Error report, which is meant to be sent back to the client, when service invocation fails.
 * <p/>
 * This is a way, to structure error reports as json documents, for REST service clients.
 * <p/>
 * Created by andriy on 12/4/14.
 */
public class ErrorReport implements Dto {

    private final List<FieldError> errors;

    public ErrorReport(List<FieldError> errors) {
        this.errors = new ArrayList<>(errors);
    }

    public List<FieldError> getErrors() {
        return new ArrayList<>(errors);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ErrorReport)) return false;

        ErrorReport that = (ErrorReport) o;

        if (!errors.equals(that.errors)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return errors.hashCode();
    }

    @Override
    public String toString() {
        return "ErrorReport{" +
                "errors=" + errors +
                '}';
    }
}
