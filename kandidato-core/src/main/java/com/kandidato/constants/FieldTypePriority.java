package com.kandidato.constants;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by diykorey on 12.02.2015.
 */
public enum FieldTypePriority {
    PREDEFINED(1), PRIME(2), CUSTOM(3);
    private static Map<Integer, FieldTypePriority> lookup = new HashMap<>();

    static {
        for (FieldTypePriority type : values()) {
            lookup.put(type.priority, type);
        }
    }

    public final int priority;

    FieldTypePriority(int priority) {
        this.priority = priority;
    }

    public static FieldTypePriority get(int value) {
        FieldTypePriority priority = lookup.get(value);
        if (priority == null) {
            return CUSTOM;
        }
        return priority;
    }

}
