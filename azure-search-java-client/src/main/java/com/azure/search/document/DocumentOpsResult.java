package com.azure.search.document;

import org.codehaus.jackson.annotate.JsonProperty;

import java.util.List;

public class DocumentOpsResult {

    @JsonProperty("@odata.context")
    private String context;

    @JsonProperty("Message")
    private String message;

    private List<DocumentOpResult> value;

    public List<DocumentOpResult> getValue() {
        return value;
    }

    public void setValue(List<DocumentOpResult> value) {
        this.value = value;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
