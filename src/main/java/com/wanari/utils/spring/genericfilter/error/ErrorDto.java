package com.wanari.utils.spring.genericfilter.error;

import io.vavr.control.Either;

import java.util.ArrayList;
import java.util.List;

public class ErrorDto {
    public Integer status;
    public String entityName;
    public List<ErrorDescriptionDto> errorDescriptions;

    public ErrorDto(Integer status) {
        this.status = status;
    }

    public static <T> Either<ErrorDto, T> badRequestEither(Integer status,String entityName, String errorKey, ErrorDescriptionParamDto param) {
        return Either.left(badRequest(status, entityName, errorKey, param));
    }

    public static ErrorDto badRequest(Integer status, String entityName, String errorKey, ErrorDescriptionParamDto param) {
        ErrorDto errorDto = new ErrorDto(status);
        errorDto.entityName = entityName;
        errorDto.errorDescriptions = new ArrayList<>();
        errorDto.errorDescriptions.add(new ErrorDescriptionDto(errorKey, param));
        return errorDto;
    }
}
