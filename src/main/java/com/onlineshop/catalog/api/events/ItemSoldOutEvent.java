package com.onlineshop.catalog.api.events;

import com.onlineshop.catalog.api.CatalogAggregate;
import ru.quipy.core.annotations.DomainEvent;
import ru.quipy.domain.Event;

import java.util.Date;
import java.util.UUID;

import static com.onlineshop.catalog.api.events.ItemEventValue.ITEM_SOLD_OUT;

@DomainEvent(name = ITEM_SOLD_OUT)
public class ItemSoldOutEvent extends Event<CatalogAggregate> {
    public ItemSoldOutEvent(UUID id) {
        super(UUID.randomUUID(), ITEM_SOLD_OUT, 1, new Date().getTime());
        this.id = id;
    }
    final UUID id;
}
