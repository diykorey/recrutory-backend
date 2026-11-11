package com.azure.search.query;

import org.codehaus.jackson.annotate.JsonProperty;

import java.util.List;
import java.util.Optional;

public class SearchResult {

    @JsonProperty("@odata.context")
    private String context;

    @JsonProperty("@odata.count")
    private Integer count;

    @JsonProperty("Message")
    private String message;

    @JsonProperty("error")
    private SearchResultError error;

    @JsonProperty("value")
    private List<SearchHit> value;

    public Optional<Integer> getCount() {
        return Optional.ofNullable(count);
    }

    public List<SearchHit> getValue() {
        return value;
    }

    public String getContext() {
        return context;
    }

    public String getMessage() {
        return message;
    }

    public SearchResultError getError() {
        return error;
    }
}
