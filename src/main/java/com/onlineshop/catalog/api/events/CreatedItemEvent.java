package com.onlineshop.catalog.api.events;

import com.onlineshop.catalog.api.CatalogAggregate;
import com.onlineshop.catalog.api.ItemAggregate;
import ru.quipy.core.annotations.DomainEvent;
import ru.quipy.domain.Event;

import java.util.Date;
import java.util.UUID;

import static com.onlineshop.catalog.api.events.ItemEventValue.ITEM_ADDED_TO_THE_CATALOG;

@DomainEvent(name = ITEM_ADDED_TO_THE_CATALOG)
public class CreatedItemEvent extends Event<ItemAggregate> {

    final UUID id;
    final String name;
    final String description;
    final double price;
    final double amount;

    public CreatedItemEvent(UUID id, String name, String description, double price, double amount) {

        super(UUID.randomUUID(), ITEM_ADDED_TO_THE_CATALOG, 1, new Date().getTime());
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.amount = amount;
    }
}