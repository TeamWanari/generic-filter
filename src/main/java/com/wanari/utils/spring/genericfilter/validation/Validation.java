package com.wanari.utils.spring.genericfilter.validation;

public class Validation {
    public Validator validator;
    public ErrorMessage errorMessage;

    public Validation(Validator validator, ErrorMessage errorMessage) {
        this.validator = validator;
        this.errorMessage = errorMessage;
    }

    public boolean test(Object valueObject) {
        return validator.test(valueObject);
    }

    public boolean isInvalid(Object valueObject) {
        return validator.negate().test(valueObject);
    }
}
