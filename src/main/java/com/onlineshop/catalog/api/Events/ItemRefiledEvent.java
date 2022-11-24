package com.onlineshop.catalog.api.Events;

import com.onlineshop.catalog.api.CatalogAggregate;
import ru.quipy.core.annotations.DomainEvent;
import ru.quipy.domain.Event;

import java.util.Date;
import java.util.UUID;

import static com.onlineshop.catalog.api.Events.ItemEventValue.ITEM_REFILED;

@DomainEvent(name = ITEM_REFILED)
public class ItemRefiledEvent extends Event<CatalogAggregate> {
    public ItemRefiledEvent(UUID id, int amount) {
        super(UUID.randomUUID(), ITEM_REFILED, 1, new Date().getTime());
        this.id = id;
        this.amount = amount;
    }
    final UUID id;
    final int amount;
}
