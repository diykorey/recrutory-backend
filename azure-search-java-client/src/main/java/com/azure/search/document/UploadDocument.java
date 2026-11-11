package com.azure.search.document;

public final class UploadDocument extends DocumentOpWithFields {
    public UploadDocument(String value) {
        this("id", value);
    }

    public UploadDocument(String idName, String value) {
        getFields().put("@search.action", "upload");
        getFields().put(idName, value);
    }
}
