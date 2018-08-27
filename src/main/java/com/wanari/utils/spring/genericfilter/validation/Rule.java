package com.wanari.utils.spring.genericfilter.validation;

import java.util.function.Predicate;

@FunctionalInterface
public interface Rule extends Predicate<Object> {
}