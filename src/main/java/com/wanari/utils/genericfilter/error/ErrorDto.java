package com.wanari.utils.genericfilter.error;

import java.util.ArrayList;
import java.util.List;

public class ErrorDto {
    public String entityName;
    public List<ErrorDescriptionDto> errorDescriptions;

    public static ErrorDto from(String entityName, String errorKey, ErrorDescriptionParamDto param) {
        ErrorDto errorDto = new ErrorDto();
        errorDto.entityName = entityName;
        errorDto.errorDescriptions = new ArrayList<>();
        errorDto.errorDescriptions.add(new ErrorDescriptionDto(errorKey, param));
        return errorDto;
    }
}
