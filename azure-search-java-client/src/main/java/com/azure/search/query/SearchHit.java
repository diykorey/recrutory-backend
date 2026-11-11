package com.azure.search.query;

import com.azure.search.type.GeographyPoint;
import org.codehaus.jackson.annotate.JsonAnySetter;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class SearchHit {

    private final Map<String, Object> fields = new LinkedHashMap<>();

    @JsonProperty("@search.score")
    private double score;

    @JsonProperty("@search.highlights")
    private Highlights highlights;

    @JsonAnySetter
    public void set(String name, Object obj) {
        fields.put(name, obj);
    }

    public Optional<String> getStringField(String name){
        return Optional.ofNullable((String) fields.get(name));
    }

    public Optional<Integer> getIntegerField(String name){
        return Optional.ofNullable((Integer) fields.get(name));
    }

    public Optional<Long> getLongField(String name){
        return Optional.ofNullable((Long)fields.get(name));
    }

    public Optional<Double> getDoubleField(String name){
        return Optional.ofNullable((Double) fields.get(name));
    }

    public Optional<List<String>> getStringListField(String name){
        return Optional.ofNullable((List<String>) fields.get(name));
    }

    public Optional<Boolean> getBooleanField(String name){
        return Optional.ofNullable((Boolean) fields.get(name));
    }

    public Optional<GeographyPoint> getGeographyPointField(String name){
        if(fields.containsKey(name)){
            Map<String, Object> geo = (Map<String, Object>) fields.get(name);
            List<Double> coordinates = (List<Double>) geo.get("coordinates");

            return Optional.of(new GeographyPoint(//
                    coordinates.get(0),
                    coordinates.get(1))
            );
        }
        return Optional.empty();
    }


    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public Highlights getHighlights() {
        return highlights;
    }

    public void setHighlights(Highlights highlights) {
        this.highlights = highlights;
    }
}
