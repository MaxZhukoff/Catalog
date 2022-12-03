package ru.onlineshop.catalog.logic

import ru.onlineshop.catalog.api.*
import ru.quipy.core.annotations.StateTransitionFunc
import ru.quipy.domain.AggregateState
import java.util.*

class CatalogAggregateState : AggregateState<String, CatalogAggregate> {
    private lateinit var catalogId: String

    var items: MutableMap<UUID, Item> = mutableMapOf()

    override fun getId() = catalogId

    fun createCatalog(): CatalogCreateEvent {
        return CatalogCreateEvent()
    }

    @StateTransitionFunc
    fun createCatalog(event: CatalogCreateEvent) {
        catalogId = event.catalogId
    }

    fun addItemToCatalog(
        itemId: UUID = UUID.randomUUID(),
        title: String,
        description: String,
        price: Int,
        amount: Int
    ): ItemAddedEvent {

        if (items.containsKey(itemId)) {
            throw IllegalArgumentException("Item with id $itemId is already exists")
        }

        return ItemAddedEvent(itemId, title, description, amount, price)
    }

    @StateTransitionFunc
    fun addItemToCatalog(event: ItemAddedEvent) {
        items[event.itemId] = Item(event.itemId, event.title, event.description, event.price, event.amount)
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

    fun changeItemPrice(itemId: UUID, newPrice: Int): ItemPriceChangedEvent {
        if (!items.containsKey(itemId)) {
            throw IllegalArgumentException("No such item with id $itemId")
        }

        return ItemPriceChangedEvent(itemId, newPrice)
    }

    @StateTransitionFunc
    fun changeItemPrice(event: ItemPriceChangedEvent) {
        items[event.itemId]!!.price = event.itemPrice
    }

    fun changeItemAmount(itemId: UUID, amountChangeTo: Int): ItemAmountChangedEvent {
        if (!items.containsKey(itemId)) {
            throw IllegalArgumentException("No such item with id $itemId")
        }

        if (amountChangeTo < 0 && items[itemId]!!.amount + amountChangeTo < 0) {
            throw IllegalStateException("You can't store amount of item less than 0")
        }

        if (items[itemId]!!.amount + amountChangeTo == 0) {
            ItemSoldOutEvent(itemId)
        } else if (items[itemId]!!.amount == 0 && amountChangeTo > 0) {
            ItemRefiledEvent(itemId, amountChangeTo)
        }

        return ItemAmountChangedEvent(itemId, amountChangeTo)
    }

    @StateTransitionFunc
    fun changeItemAmount(event: ItemAmountChangedEvent) {
        items[event.itemId]!!.amount += event.amountChangeTo
    }

    @StateTransitionFunc
    fun itemSoldOut(event: ItemSoldOutEvent) {
    }

    @StateTransitionFunc
    fun itemRefiled(event: ItemRefiledEvent) {
    }
}

data class Item(
    val id: UUID,
    val title: String,
    val description: String,
    var price: Int,
    var amount: Int
)