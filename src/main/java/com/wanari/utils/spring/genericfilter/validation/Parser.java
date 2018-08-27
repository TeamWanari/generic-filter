package com.wanari.utils.spring.genericfilter.validation;

import java.util.function.Function;

@FunctionalInterface
public interface Parser<T> extends Function<Object, T> {
}