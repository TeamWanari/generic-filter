package com.wanari.utils.genericfilter.validation;

import java.util.function.Predicate;

@FunctionalInterface
public interface Validator extends Predicate<Object> {
}