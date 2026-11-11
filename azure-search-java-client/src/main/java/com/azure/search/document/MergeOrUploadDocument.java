package com.azure.search.document;

public final class MergeOrUploadDocument extends DocumentOpWithFields {
    public MergeOrUploadDocument(String value) {
        this("id", value);
    }

    public MergeOrUploadDocument(String idName, String value) {
        getFields().put("@search.action", "mergeOrUpload");
        getFields().put(idName, value);
    }
}
