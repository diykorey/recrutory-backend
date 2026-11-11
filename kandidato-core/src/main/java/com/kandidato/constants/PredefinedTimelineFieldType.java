package com.kandidato.constants;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by diykorey on 12.02.2015.
 */
public enum PredefinedTimelineFieldType {
    NAME_SURNAME(   ), SKYPE, EMAIL, PHONE, ADDRESS;

    private static Map<String, PredefinedTimelineFieldType> lookup = new HashMap<>();

    static {
        for (PredefinedTimelineFieldType type : values()) {
            lookup.put(type.name(), type);
        }
    }

    public static PredefinedTimelineFieldType get(String name) {
        return lookup.get(name);
    }

}
