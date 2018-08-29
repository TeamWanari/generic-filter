package com.wanari.utils.genericfilter.builder;

import java.util.function.Consumer;

public class OnPath<T> extends BasePath<T> {

    public OnPath(String key, Consumer<BasePath<T>> addFilterCallback) {
        super(key, addFilterCallback);
    }

    public ValuePath<T> withValue(Object value) {
        this.value = value;
        return new ValuePath<>(this);
    }
}
