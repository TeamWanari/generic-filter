package com.wanari.utils.genericfilter;

import com.wanari.utils.genericfilter.builder.BasePath;
import com.wanari.utils.genericfilter.validation.Parser;
import com.wanari.utils.genericfilter.validation.Rule;
import com.wanari.utils.genericfilter.validation.Validation;

public class GenericCondition<T> {
    private String key;
    private Object value;
    private Validation validation;
    private Rule rule;
    private Parser<T> parser;

    public GenericCondition(BasePath<T> builder) {
        this.key = builder.key;
        this.value = builder.value;
        this.validation = builder.validation;
        this.rule = builder.rule;
        this.parser = builder.parser;
    }

    public String getKey() {
        return key;
    }

    @SuppressWarnings("unchecked")
    public T getValue() {
        if(value == null) {
            return null;
        }

        if(parser == null) {
            return (T) value;
        } else {
            return parser.apply(value);
        }
    }

    public boolean isInvalid() {
        if(value == null || validation == null) {
            return false;
        } else {
            return validation.isInvalid(value);
        }
    }

    public String getError() {
        return validation.errorMessage.apply(key, value);
    }

    boolean shouldAddToQueryFilters() {
        if(rule == null) {
            return true;
        } else {
            return rule.test(value);
        }
    }
}
