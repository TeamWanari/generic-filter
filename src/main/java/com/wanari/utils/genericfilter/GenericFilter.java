package com.wanari.utils.genericfilter;


import com.wanari.utils.genericfilter.builder.BasePath;
import com.wanari.utils.genericfilter.builder.OnPath;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GenericFilter {
    private List<GenericCondition> conditions = new ArrayList<>();

    public <T> OnPath<T> on(String key) {
        return new OnPath<>(key, this::add);
    }

    Map<String, Object> toQueryFilters() {
        return conditions.stream()
            .filter(GenericCondition::shouldAddToQueryFilters)
            .collect(Collectors.toMap(GenericCondition::getKey, GenericCondition::getValue));
    }

    public List<String> getErrors() {
        return conditions.stream()
            .filter(GenericCondition::isInvalid)
            .map(GenericCondition::getError)
            .collect(Collectors.toList());
    }

    private <T> void add(BasePath<T> builder) {
        conditions.add(new GenericCondition<>(builder));
    }
}
