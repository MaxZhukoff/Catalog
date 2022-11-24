package com.onlineshop.catalog.api.Events;

import com.onlineshop.catalog.api.CatalogAggregate;
import ru.quipy.core.annotations.DomainEvent;
import ru.quipy.domain.Event;

import java.util.Date;
import java.util.UUID;

import static com.onlineshop.catalog.api.Events.ItemEventValue.ITEM_REMOVED_FROM_THE_CATALOG;

@DomainEvent(name = ITEM_REMOVED_FROM_THE_CATALOG)
public class ItemRemovedFromTheCatalogEvent extends Event<CatalogAggregate> {
    public ItemRemovedFromTheCatalogEvent(UUID id) {
        super(UUID.randomUUID(), ITEM_REMOVED_FROM_THE_CATALOG, 1, new Date().getTime());
        this.id = id;
    }
    final UUID id;
}
