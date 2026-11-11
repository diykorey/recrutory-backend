package com.azure.search.document;


import java.util.LinkedHashMap;
import java.util.Map;

public abstract class DocumentOp {

    private final Map<String, Object> fields = new LinkedHashMap<>();

    public Map<String, Object> getFields() {
        return fields;
    }

}
