package com.onlineshop.catalog.logic;

import com.onlineshop.catalog.api.CatalogAggregate;
import com.onlineshop.catalog.api.Events.*;
import com.onlineshop.catalog.entity.ItemEntity;
import org.jetbrains.annotations.Nullable;
import ru.quipy.domain.AggregateState;

import java.util.ArrayList;
import java.util.UUID;

public class Catalog implements AggregateState<UUID, CatalogAggregate> {
    private UUID id;

    @Nullable
    @Override
    public UUID getId() {
        return id;
    }

    private ArrayList<ItemEntity> Items = new ArrayList<>();

    public CatalogCreatedEvent CreateNewCatalog(){
        id = UUID.randomUUID();
        return new CatalogCreatedEvent(id);
    }

    public ItemAddedToTheCatalogEvent AddItemToCatalog (ItemEntity item){
        if(Items.contains(item)){throw new IllegalArgumentException("Item already exists");}
        return new ItemAddedToTheCatalogEvent(item.getId(), item.getName(), item.getDescription(), item.getPrice(), item.getAmount());
    }

    public ItemRemovedFromTheCatalogEvent RemoveItemFromCatalog(ItemEntity item){
        if(!Items.contains(item)){
            throw new IllegalArgumentException("No such item");
        }
        Items.remove(item);
        return new ItemRemovedFromTheCatalogEvent(item.getId());
    }
    public ItemPriceChangedEvent ChangeItemPrice(ItemEntity item, int price){
        if(!Items.contains(item)){
            throw new IllegalArgumentException("No such item");
        }
        for (ItemEntity ouritem : Items){
            if(ouritem.equals(item)){
                ouritem.setPrice(price);
            }
        }
        return new ItemPriceChangedEvent(item.getId(), item.getPrice());
    }
    public ItemRefiledEvent RefilItem(ItemEntity item, int amount){
        if(!Items.contains(item)){
            throw new IllegalArgumentException("No such item");
        }
        for (ItemEntity ouritem : Items){
            if(ouritem.equals(item)){
                ouritem.setAmount(amount);
            }
        }
        return new ItemRefiledEvent(item.getId(), item.getAmount());
    }
    //TODO переписать контролер
    //TODO проверьте правильность методов)
}