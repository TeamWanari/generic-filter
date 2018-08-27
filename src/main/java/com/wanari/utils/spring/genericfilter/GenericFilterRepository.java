package com.wanari.utils.spring.genericfilter;


import com.wanari.utils.spring.genericfilter.constant.GeneralFilterConstants;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import javax.persistence.metamodel.SingularAttribute;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

public interface GenericFilterRepository<Dto, Entity> {

    EntityManager em();

    default List<Dto> find(ValidGenericParameters parameter, Class<Entity> clazz, BiFunction<Entity, List<String>, Dto> mapper) {
        return getEntities(parameter, clazz).stream()
            .map(p -> mapper.apply(p, parameter.fields))
            .collect(Collectors.toList());
    }


    default List<Dto> findForGraphql(ValidGenericParameters parameter, Class<Entity> clazz, Function<Entity, Dto> mapper) {
        return getEntities(parameter, clazz).stream()
            .map(mapper)
            .collect(Collectors.toList());
    }

    default List<Entity> getEntities(ValidGenericParameters parameter, Class<Entity> clazz) {
        CriteriaBuilder cb = em().getCriteriaBuilder();
        CriteriaQuery<Entity> query = cb.createQuery(clazz);

        Root<Entity> entity = query.from(clazz);
        SingularAttribute<Entity, ?> orderByAttribute = getOrderByAttribute(parameter.sortBy);
        Order order = getOrder(cb, orderByAttribute, parameter.sortOrder, entity);
        Predicate where = getWhereConditions(query, cb, parameter.filters, entity);

        if(parameter.shouldJoinTables) {
            joinTables(entity, parameter.fields, new JoinTablesData());
        }

        TypedQuery<Entity> typedQuery = createTypedQuery(query, entity, order, where);

        List<Entity> entities;
        if(parameter.page != null && parameter.pageSize != null) {
            entities = typedQuery
                .setFirstResult(parameter.page * parameter.pageSize)
                .setMaxResults(parameter.pageSize)
                .getResultList();
        } else {
            entities = typedQuery.getResultList();
        }
        return entities;
    }

    default TypedQuery<Entity> createTypedQuery(CriteriaQuery<Entity> query, Root<Entity> entity, Order order, Predicate where) {
        if(where != null) {
            return em().createQuery(query
                .select(entity)
                .orderBy(order)
                .where(where)
                .distinct(true)
            );
        } else {
            return em().createQuery(query
                .select(entity)
                .orderBy(order)
                .distinct(true)
            );
        }
    }

    void joinTables(FetchParent<?, Entity> entity, List<String> fields, JoinTablesData tables);

    SingularAttribute<Entity, ?> getOrderByAttribute(String sortBy);

    Predicate getWhereConditions(CriteriaQuery<Entity> query, CriteriaBuilder cb, Map<String, Object> filters, Root<Entity> entity);

    default Order getOrder(CriteriaBuilder cb, SingularAttribute<Entity, ?> orderByAttribute, String sortOrder, Root<Entity> entity) {
        Path sortBy = entity.get(orderByAttribute);
        switch(sortOrder) {
            case GeneralFilterConstants.ASC:
                return cb.asc(sortBy);
            case GeneralFilterConstants.DESC:
                return cb.desc(sortBy);
            default:
                return cb.asc(sortBy);
        }
    }

    default Predicate combinePredicates(List<Predicate> predicates, BiFunction<Expression<Boolean>, Expression<Boolean>, Predicate> accumulator) {
        if(predicates.size() == 0) {
            return null;
        } else {
            if(predicates.size() == 1) {
                return predicates.get(0);
            } else {
                Predicate head = predicates.get(0);
                List<Predicate> tail = predicates.subList(1, predicates.size());
                return tail.stream().reduce(head, accumulator::apply);
            }
        }
    }
}
