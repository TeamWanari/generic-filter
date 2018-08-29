package com.wanari.utils.genericfilter.error;

import java.util.ArrayList;
import java.util.List;

public class ErrorDescriptionDto {
    public String errorKey;
    public List<ErrorDescriptionParamDto> params;

    public ErrorDescriptionDto(String errorKey, ErrorDescriptionParamDto param) {
        this.errorKey = errorKey;
        this.params = new ArrayList<>();
        this.params.add(param);
    }
}
