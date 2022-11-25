package com.onlineshop.catalog.api.events;

import com.onlineshop.catalog.api.CatalogAggregate;
import ru.quipy.core.annotations.DomainEvent;
import ru.quipy.domain.Event;

import java.util.Date;
import java.util.UUID;

import static com.onlineshop.catalog.api.events.ItemEventValue.ITEM_REMOVED_FROM_THE_CATALOG;

@DomainEvent(name = ITEM_REMOVED_FROM_THE_CATALOG)
public class itemRemovedFromTheCatalogEvent extends Event<CatalogAggregate> {
    public itemRemovedFromTheCatalogEvent(UUID id) {
        super(UUID.randomUUID(), ITEM_REMOVED_FROM_THE_CATALOG, 1, new Date().getTime());
        this.id = id;
    }
    final UUID id;
}
