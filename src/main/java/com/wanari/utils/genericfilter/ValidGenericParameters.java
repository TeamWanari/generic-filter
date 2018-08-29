package com.wanari.utils.genericfilter;

import java.util.List;
import java.util.Map;

public class ValidGenericParameters {
    public List<String> fields;
    public String sortBy;
    public String sortOrder;
    public Map<String, Object> filters;
    public Integer page;
    public Integer pageSize;
    public Boolean shouldJoinTables;

    private ValidGenericParameters() {
    }

    ValidGenericParameters(GenericParameters parameters) {
        this.fields = parameters.fields;
        this.sortBy = parameters.sortBy;
        this.sortOrder = parameters.sortOrder;
        this.filters = parameters.filter.toQueryFilters();
        this.page = parameters.page;
        this.pageSize = parameters.pageSize;
        this.shouldJoinTables = parameters.shouldJoinTables;
    }
}
