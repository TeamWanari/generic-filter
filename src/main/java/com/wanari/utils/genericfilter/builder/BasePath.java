package com.wanari.utils.genericfilter.builder;

import com.wanari.utils.genericfilter.validation.Parser;
import com.wanari.utils.genericfilter.validation.Rule;
import com.wanari.utils.genericfilter.validation.Validation;

import java.util.function.Consumer;

public class BasePath<T> {
    private Consumer<BasePath<T>> addFilterCallback;

    public String key;
    public Object value;
    public Validation validation;
    public Rule rule;
    public Parser<T> parser;

    BasePath(String key, Consumer<BasePath<T>> addFilterCallback) {
        this.key = key;
        this.addFilterCallback = addFilterCallback;
    }

    BasePath(BasePath<T> other) {
        this(
            other.addFilterCallback,

            other.key,
            other.value,
            other.validation,
            other.rule,
            other.parser
        );
    }

    private BasePath(Consumer<BasePath<T>> addFilterCallback, String key, Object value, Validation validation, Rule rule, Parser<T> parser) {
        this.addFilterCallback = addFilterCallback;

        this.key = key;
        this.value = value;
        this.validation = validation;
        this.rule = rule;
        this.parser = parser;
    }

    public void addFilter() {
        addFilterCallback.accept(this);
    }
}
