package com.azure.search.document;

public final class MergeDocument extends DocumentOpWithFields {
    public MergeDocument(String value) {
        this("id", value);
    }

    public MergeDocument(String idName, String value) {
        getFields().put("@search.action", "merge");
        getFields().put(idName, value);
    }
}
