package com.wanari.utils.spring.genericfilter;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collector;

public interface GenericMapper<Dto, Entity> {

    Dto mapWithFields(Entity entity, List<String> fields);

    default <Container extends Collection<? extends Dto>> Container mapWithFields(Collection<? extends Entity> entities, List<String> fields, Collector<Dto, ?, Container> collector) {
        return entities.stream()
            .map(entity -> this.mapWithFields(entity, fields))
            .collect(collector);
    }
}
