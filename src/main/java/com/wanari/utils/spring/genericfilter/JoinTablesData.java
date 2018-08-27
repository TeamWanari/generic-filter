package com.wanari.utils.spring.genericfilter;

import javax.persistence.criteria.Fetch;
import javax.persistence.criteria.FetchParent;
import javax.persistence.criteria.JoinType;
import java.util.HashMap;
import java.util.Map;

public class JoinTablesData {
    private Map<String, Fetch> joins = new HashMap<>();

    @SuppressWarnings("unchecked")
    public <P, C> Fetch<P, C> leftJoinFetch(FetchParent<?, P> parent, JoinTableInfo<P, C> info) {
        if(joins.containsKey(info.joinKey)) {
            return (Fetch<P, C>) joins.get(info.joinKey);
        } else {
            Fetch<P, C> join;
            switch(info.attributeType) {
                case SINGULAR:
                    join = parent.fetch(info.singularAttribute, JoinType.LEFT);
                    break;
                case SET:
                    join = parent.fetch(info.setAttribute, JoinType.LEFT);
                    break;
                default:
                    throw new IllegalArgumentException("Unknown enum: " + info.attributeType);
            }
            joins.put(info.joinKey, join);
            return join;
        }
    }
}
