package com.azure.search.document;

import com.azure.search.type.GeographyPoint;

import java.util.Collection;

public abstract class DocumentOpWithFields extends DocumentOp {

    public DocumentOpWithFields addField(String name, String value) {
        return addGeneralField(name, value);
    }

    public DocumentOpWithFields addField(String name, Integer value) {
        return addGeneralField(name, value);
    }

    public DocumentOpWithFields addField(String name, Long value) {
        return addGeneralField(name, value);
    }

    public DocumentOpWithFields addField(String name, Double value) {
        return addGeneralField(name, value);
    }

    public DocumentOpWithFields addField(String name, Collection<String> value) {
        return addGeneralField(name, value);
    }

    public DocumentOpWithFields addField(String name, Boolean value) {
        return addGeneralField(name, value);
    }

    public DocumentOpWithFields addField(String name, GeographyPoint value) {
        return addGeneralField(name, value);
    }

    private DocumentOpWithFields addGeneralField(String name, Object value) {
        getFields().put(name, value);
        return this;
    }
}
