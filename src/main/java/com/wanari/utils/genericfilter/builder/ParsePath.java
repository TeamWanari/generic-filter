package com.wanari.utils.genericfilter.builder;

import com.wanari.utils.genericfilter.validation.Parser;

public class ParsePath<T> extends BasePath<T> {

    ParsePath(BasePath<T> other) {
        super(other);
    }

    public EndPath<T> andParse(Parser<T> parser) {
        this.parser = parser;
        return new EndPath<>(this);
    }
}
