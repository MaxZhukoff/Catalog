package com.onlineshop.catalog.logic;

import com.onlineshop.catalog.api.ItemAggregate;
import com.onlineshop.catalog.api.events.CreatedItemEvent;
import com.onlineshop.catalog.api.events.ItemPriceChangedEvent;
import com.onlineshop.catalog.api.events.ItemRefiledEvent;
import com.onlineshop.catalog.api.events.ItemRemovedFromTheCatalogEvent;
import com.onlineshop.catalog.entity.ItemEntity;
import org.jetbrains.annotations.Nullable;
import ru.quipy.core.annotations.StateTransitionFunc;
import ru.quipy.domain.AggregateState;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Item implements AggregateState<UUID, ItemAggregate> {

    private UUID itemId;
/*    private List<ItemEntity> items = new ArrayList<>();*/

    @Nullable
    @Override
    public UUID getId() {
        return itemId;
    }

    /**
     * add item to the catalog
     */
    public CreatedItemEvent createItem(String name, String description, double price, double amount) {

        return new CreatedItemEvent(UUID.randomUUID(), name, description, price, amount);
    }

    @StateTransitionFunc
    public void createNewItem(CreatedItemEvent event) {
        itemId = event.getId();
    }

/*    public ItemRemovedFromTheCatalogEvent removeItemFromCatalog(ItemEntity item) {

        if (!items.contains(item)) {

            throw new IllegalArgumentException("No such item");
        }

        items.remove(item);

        return new ItemRemovedFromTheCatalogEvent(item.getId());
    }*/

/*    public ItemPriceChangedEvent changeItemPrice(ItemEntity item, int price) {

        if (!items.contains(item)) {

            throw new IllegalArgumentException("No such item");
        }

        for (ItemEntity ouritem : Items) {

            if (ouritem.equals(item)) {

                ouritem.setPrice(price);
            }
        }

        return new ItemPriceChangedEvent(item.getId(), item.getPrice());
    }

    public ItemRefiledEvent refilItem(ItemEntity item, int amount) {

        if (!Items.contains(item)) {

            throw new IllegalArgumentException("No such item");
        }

        for (ItemEntity ourItem : Items) {

            if (ourItem.equals(item)) {

                ourItem.setAmount(amount);
            }
        }

        return new ItemRefiledEvent(item.getId(), item.getAmount());
    }*/
}
