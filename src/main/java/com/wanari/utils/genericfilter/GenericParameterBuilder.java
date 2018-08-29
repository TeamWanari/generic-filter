package com.wanari.utils.genericfilter;

import java.util.List;

public class GenericParameterBuilder {
    private GenericFilter filter;
    private String sortBy;
    private String sortOrder;
    private List<String> fields;
    private Integer page;
    private Integer pageSize;
    private Boolean shouldJoinTables;

    public static GenericParameterBuilder filterWith(GenericFilter filter) {
        GenericParameterBuilder builder = new GenericParameterBuilder();
        builder.filter = filter;
        return builder;
    }


    public GenericParameterBuilder sortBy(Object sortBy) {
        this.sortBy = (String) sortBy;
        return this;
    }

    public GenericParameterBuilder sortOrder(Object sortOrder) {
        this.sortOrder = (String) sortOrder;
        return this;
    }

    public GenericParameterBuilder page(Object page) {
        if(page instanceof Integer) {
            this.page = (Integer) page;
            return this;
        } else if(page instanceof String) {
            this.page = Integer.valueOf((String) page);
            return this;
        } else {
            throw new UnsupportedOperationException("Cannot make Integer out of " + page.getClass());
        }
    }

    public GenericParameterBuilder pageSize(Object pageSize) {
        if(pageSize instanceof Integer) {
            this.pageSize = (Integer) pageSize;
            return this;
        } else if(pageSize instanceof String) {
            this.pageSize = Integer.valueOf((String) pageSize);
            return this;
        } else {
            throw new UnsupportedOperationException("Cannot make Integer out of " + pageSize.getClass());
        }
    }

    public GenericParameterBuilder withFields(List<String> fields) {
        this.fields = fields;
        return this;
    }

    public GenericParameterBuilder joinTables(Boolean shouldJoinTables) {
        this.shouldJoinTables = shouldJoinTables;
        return this;
    }

    public GenericParameters build() {
        GenericParameters parameter = new GenericParameters();
        parameter.fields = fields;
        parameter.sortBy = sortBy;
        parameter.sortOrder = sortOrder;
        parameter.page = page;
        parameter.pageSize = pageSize;
        parameter.filter = filter;
        parameter.shouldJoinTables = shouldJoinTables;
        return parameter;
    }
}
