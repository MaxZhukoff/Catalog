package ru.onlineshop.catalog.logic

import ru.onlineshop.catalog.api.*
import ru.quipy.core.annotations.StateTransitionFunc
import ru.quipy.domain.AggregateState
import java.util.*

class CatalogAggregateState : AggregateState<String, CatalogAggregate> {
    private var catalogId: String = "catalog"

    var items: MutableMap<UUID, Item> = mutableMapOf()

    override fun getId() = catalogId

    fun addItemToCatalog(itemId: UUID = UUID.randomUUID(), title: String, description: String, price: Int, amount: Int)
            : ItemAddedEvent {
        println(catalogId)
        if (items.containsKey(itemId)) {
            throw IllegalArgumentException("Item with id $itemId is already exists")
        }
        return ItemAddedEvent(itemId, title, description, amount, price)
    }

    @StateTransitionFunc
    fun addItemToCatalog(event: ItemAddedEvent) {
        items[event.itemId] = Item(event.itemId, event.title, event.description, event.price, event.amount)
    }


//    fun deleteItemFromCatalog (id : UUID) : ItemRemovedEvent {
//        if (!itemList.containsKey(id)){
//            throw IllegalArgumentException("No such item with id $id")
//        }
//        return ItemRemovedEvent(id)
//    }
//    fun changeItemPrice (id : UUID, price : Int) : ItemPriceChangedEvent {
//        if (!itemList.containsKey(id)){
//            throw IllegalArgumentException("No such item with id $id")
//        }
//        return ItemPriceChangedEvent(id, price)
//    }
//    fun changeItemAmount(id : UUID, amount : Int) : ItemRefiledEvent {
//        if (!itemList.containsKey(id)){
//            throw IllegalArgumentException("No such item with id $id")
//        }
//        return ItemRefiledEvent(id, amount)
//    }
    //TODO не уверен надо делать изменение количества товара или только его пополнения, если что пофиксите
}

data class Item(
    val id: UUID,
    val title: String,
    val description: String,
    val price: Int,
    val amount: Int
)