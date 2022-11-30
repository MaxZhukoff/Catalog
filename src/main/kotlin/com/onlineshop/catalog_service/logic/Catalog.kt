package com.onlineshop.catalog_service.logic

import com.onlineshop.catalog_service.api.*
import ru.quipy.domain.AggregateState
import java.lang.IllegalArgumentException
import java.util.*
import kotlin.collections.ArrayList

class Catalog : AggregateState<UUID, CatalogAggregate>{
    private lateinit var id: UUID
    var ItemList: MutableMap<UUID, Item> = mutableMapOf()

    override fun getId() = id

    fun createCatalog(id: UUID = UUID.randomUUID()) : CatalogCreatedEvent{
        return CatalogCreatedEvent(id)
    }

    fun addItemToCatalog (id: UUID, name : String, description: String, amount: Int, price: Int, category: String) : ItemAddedEvent{
        if(ItemList.containsKey(id)){
            throw IllegalArgumentException("Item with id $id is already exists")
        }
        //TODO хз, надо ли создавать итем и добавлять его в мапу
        return ItemAddedEvent(id, name, description, amount, price, category)
    }
    fun deleteIttemFromCatalog (id : UUID) : ItemRemovedEvent{
        if (!ItemList.containsKey(id)){
            throw IllegalArgumentException("No such item with id $id")
        }
        return ItemRemovedEvent(id)
    }
    fun changeItemPrice (id : UUID, price : Int) : ItemPriceChangedEvent{
        if (!ItemList.containsKey(id)){
            throw IllegalArgumentException("No such item with id $id")
        }
        return ItemPriceChangedEvent(id, price)
    }
    fun changeItemAmount(id : UUID, amount : Int) : ItemRefiledEvent{
        if (!ItemList.containsKey(id)){
            throw IllegalArgumentException("No such item with id $id")
        }
        return ItemRefiledEvent(id, amount)
    }
    //TODO не уверен надо делать изменение количества товара или только его пополнения, если что пофиксите
}

data class Item(
    val id : UUID,
    val name : String,
    val description : String,
    val price : Int,
    val amount : Int,
    val category : String,
)