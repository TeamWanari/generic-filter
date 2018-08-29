package com.wanari.utils.genericfilter.validation;

import io.vavr.Function2;

@FunctionalInterface
public interface ErrorMessage extends Function2<String, Object, String> {
}