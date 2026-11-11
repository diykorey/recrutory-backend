package com.kandidato.model.dto.error;

/**
 * Created by andriy on 12/4/14.
 */
public class FieldError {

    private final String field;

    private final String message;

    public FieldError(String field, String message) {
        this.field = field;
        this.message = message;
    }

    public String getField() {
        return field;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FieldError)) return false;

        FieldError that = (FieldError) o;

        if (!message.equals(that.message)) return false;
        if (!field.equals(that.field)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = field.hashCode();
        result = 31 * result + message.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "FieldError{" +
                "field='" + field + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
