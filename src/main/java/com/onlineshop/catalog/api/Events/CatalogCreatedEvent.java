package com.onlineshop.catalog.api.Events;

import com.onlineshop.catalog.api.CatalogAggregate;
import ru.quipy.core.annotations.DomainEvent;
import ru.quipy.domain.Event;

import java.util.Date;
import java.util.UUID;

import static com.onlineshop.catalog.api.Events.ItemEventValue.CATALOG_CREATED;


@DomainEvent(name = CATALOG_CREATED)
public class CatalogCreatedEvent extends Event<CatalogAggregate> {
    public CatalogCreatedEvent(UUID id) {
        super(UUID.randomUUID(), CATALOG_CREATED, 1, new Date().getTime());
        this.id = id;
    }
    final UUID id;
}

//TODO разобраться с версией к нострукторе, зачем она и какую надо ставить
