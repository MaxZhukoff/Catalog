package com.onlineshop.catalog.api.events;

import com.onlineshop.catalog.api.CatalogAggregate;
import ru.quipy.core.annotations.DomainEvent;
import ru.quipy.domain.Event;

import java.util.Date;
import java.util.UUID;

import static com.onlineshop.catalog.api.events.ItemEventValue.ITEM_REFILED;

@DomainEvent(name = ITEM_REFILED)
public class ItemRefiledEvent extends Event<CatalogAggregate> {
    public ItemRefiledEvent(UUID id, long amount) {
        super(UUID.randomUUID(), ITEM_REFILED, 1, new Date().getTime());
        this.id = id;
        this.amount = amount;
    }
    final UUID id;
    final long amount;
}
