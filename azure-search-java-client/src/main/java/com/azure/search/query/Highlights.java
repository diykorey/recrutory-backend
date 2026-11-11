package com.azure.search.query;

import org.codehaus.jackson.annotate.JsonAnySetter;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Highlights {

    @JsonProperty("text@odata.type")
    private String textType;

    private final Map<String, List<String>> fields = new HashMap<>();

    @JsonAnySetter
    public void set(String name, List<String> text){
        fields.put(name, text);
    }
}
