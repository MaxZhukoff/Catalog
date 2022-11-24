package com.onlineshop.catalog.api.Events;

import com.onlineshop.catalog.api.CatalogAggregate;
import ru.quipy.core.annotations.DomainEvent;
import ru.quipy.domain.Event;

import java.util.Date;
import java.util.UUID;

import static com.onlineshop.catalog.api.Events.ItemEventValue.ITEM_PRICE_CHANGED;

@DomainEvent(name = ITEM_PRICE_CHANGED)
public class ItemPriceChangedEvent extends Event<CatalogAggregate>{
    public ItemPriceChangedEvent(UUID id, int price) {
        super(UUID.randomUUID(), ITEM_PRICE_CHANGED, 1, new Date().getTime());
        this.id = id;
        this.price = price;
    }
    final UUID id;
    final int price;
}
