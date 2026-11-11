package com.azure.search.document;

public final class DeleteDocument extends DocumentOp {

   public DeleteDocument(String value) {
        this("id", value);
    }

    public DeleteDocument(String idName, String value) {
        getFields().put("@search.action", "delete");
        getFields().put(idName, value);
    }
}
