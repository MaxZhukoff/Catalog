package ru.onlineshop.catalog.logic

import ru.onlineshop.catalog.api.*
import ru.quipy.core.annotations.StateTransitionFunc
import ru.quipy.domain.AggregateState
import java.util.*

class CatalogAggregateState : AggregateState<String, CatalogAggregate> {
    private lateinit var catalogId: String

    var items: MutableMap<UUID, Item> = mutableMapOf()

    override fun getId() = catalogId

    fun addItemToCatalog(itemId: UUID = UUID.randomUUID(), title: String, description: String, price: Int, amount: Int)
            : ItemAddedEvent {
        if (items.containsKey(itemId)) {
            throw IllegalArgumentException("Item with id $itemId is already exists")
        }
        return ItemAddedEvent(itemId, title, description, amount, price)
    }
    @StateTransitionFunc
    fun addItemToCatalog(event: ItemAddedEvent) {
        items[event.itemId] = Item(event.itemId, event.title, event.description, event.price, event.amount)
    }

    fun createCatalog(): CatalogCreateEvent {
        return CatalogCreateEvent()
    }
    @StateTransitionFunc
    fun createCatalog(event: CatalogCreateEvent) {
        catalogId = event.catalogId
    }

    fun deleteItemFromCatalog(itemId: UUID): ItemRemovedEvent {
        if (!items.containsKey(itemId)) {
            throw IllegalArgumentException("No such item with id $itemId")
        }
        return ItemRemovedEvent(itemId)
    }
    @StateTransitionFunc
    fun deleteItemFromCatalog(event: ItemRemovedEvent) {
        items.remove(event.itemId)
    }

    fun changeAmountItem(itemId: UUID, amountChangeTo: Int): ItemAmountChangedEvent {
        if (!items.containsKey(itemId)) {
            throw IllegalArgumentException("No such item with id $itemId")
        }
        return ItemAmountChangedEvent(itemId, amountChangeTo)
    }
    @StateTransitionFunc
    fun changeAmountItem(event: ItemAmountChangedEvent) {
        items[event.itemId]!!.amount += event.amountChangeTo
    }

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
    var amount: Int
)