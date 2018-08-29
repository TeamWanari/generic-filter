package com.wanari.utils.genericfilter;

import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;

public class JoinTableInfo<P, C> {
    String joinKey;
    SetAttribute<P, C> setAttribute;
    SingularAttribute<P, C> singularAttribute;
    AttributeType attributeType;

    private JoinTableInfo() {
    }

    public static <P, C> JoinTableInfo<P, C> of(SetAttribute<P, C> setAttribute, String entityName, String joinedEntityName) {
        JoinTableInfo<P, C> info = new JoinTableInfo<>();
        info.joinKey = entityName + "." + joinedEntityName;
        info.setAttribute = setAttribute;
        info.attributeType = AttributeType.SET;
        return info;
    }

    public static <P, C> JoinTableInfo<P, C> of(SingularAttribute<P, C> singularAttribute, String entityName, String joinedEntityName) {
        JoinTableInfo<P, C> info = new JoinTableInfo<>();
        info.joinKey = entityName + "." + joinedEntityName;
        info.singularAttribute = singularAttribute;
        info.attributeType = AttributeType.SINGULAR;
        return info;
    }

    enum AttributeType {
        SINGULAR, SET
    }
}
