package com.onlineshop.catalog.api.events;

import com.onlineshop.catalog.api.CatalogAggregate;
import ru.quipy.core.annotations.DomainEvent;
import ru.quipy.domain.Event;

import java.util.Date;
import java.util.UUID;

import static com.onlineshop.catalog.api.events.ItemEventValue.ITEM_ADDED_TO_THE_CATALOG;

@DomainEvent(name = ITEM_ADDED_TO_THE_CATALOG)
public class ItemAddedToTheCatalogEvent extends Event<CatalogAggregate>
{
    public ItemAddedToTheCatalogEvent(UUID id, String name, String description, int price, int amount) {
        super(UUID.randomUUID(), ITEM_ADDED_TO_THE_CATALOG, 1, new Date().getTime());
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.amount = amount;
    }
    final UUID id;
    final String name;
    final String description;
    final int price;
    final int amount;
    }

//TODO разобраться как передавать категорию
//TODO сделать ивент создания товара (хз надо ли, вроде не надо)
