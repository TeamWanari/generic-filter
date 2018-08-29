package com.wanari.utils.genericfilter;

import java.util.List;
import java.util.stream.Collectors;

public class GenericFilterUtil {

    public static List<String> getNestedFields(List<String> fields, String prefix) {
        String prefixWithDot = prefix + ".";
        return fields
            .stream()
            .filter(field -> field.startsWith(prefixWithDot))
            .map(field -> field.substring(prefixWithDot.length()))
            .collect(Collectors.toList());
    }

    public static String combine(String... elements) {
        return String.join(".", elements);
    }

    public static List<String> combine(String entityName, List<String> fields) {
        return fields.stream()
            .map(field -> combine(entityName, field))
            .collect(Collectors.toList());
    }
}
