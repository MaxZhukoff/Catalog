package ru.onlineshop.catalog.logic

import ru.onlineshop.catalog.api.*
import ru.quipy.core.annotations.StateTransitionFunc
import ru.quipy.domain.AggregateState
import ru.quipy.domain.Event
import java.util.*

class CatalogAggregateState : AggregateState<String, CatalogAggregate> {
    private lateinit var catalogId: String

    var items: MutableMap<UUID, Item> = mutableMapOf()

    override fun getId() = catalogId

    fun createCatalog(): CatalogCreateEvent {
        return CatalogCreateEvent()
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

    fun deleteItemFromCatalog(itemId: UUID): ItemRemovedEvent {
        if (!items.containsKey(itemId)) {
            throw IllegalArgumentException("No such item with id $itemId")
        }

        return ItemRemovedEvent(itemId)
    }

    fun changeItemPrice(itemId: UUID, newPrice: Int): ItemPriceChangedEvent {
        if (!items.containsKey(itemId)) {
            throw IllegalArgumentException("No such item with id $itemId")
        }

        return ItemPriceChangedEvent(itemId, newPrice)
    }

    fun refillItem(itemId: UUID, amountForRefill: Int): ItemRefiledEvent {
        if (!items.containsKey(itemId)) {
            throw IllegalArgumentException("No such item with id $itemId")
        }

        if (amountForRefill <= 0 || items[itemId]!!.amount + amountForRefill < 0) {
            throw IllegalStateException("You can't store amount of item less than or equal to 0")
        }

        return ItemRefiledEvent(itemId, amountForRefill)
    }

    fun sellItem(itemId: UUID, amountForSell: Int): Event<CatalogAggregate> {
        if (!items.containsKey(itemId)) {
            throw IllegalArgumentException("No such item with id $itemId")
        }

        if (amountForSell <= 0 || items[itemId]!!.amount - amountForSell < 0) {
            throw IllegalStateException("You can't sell item with amount less than or equal to 0")
        }

        if (items[itemId]!!.amount - amountForSell == 0) {
            return ItemSoldOutEvent(itemId, amountForSell)
        }

        return ItemSoldEvent(itemId, amountForSell)
    }

    @StateTransitionFunc
    fun createCatalog(event: CatalogCreateEvent) {
        catalogId = event.catalogId
    }

    @StateTransitionFunc
    fun addItemToCatalog(event: ItemAddedEvent) {
        items[event.itemId] = Item(event.itemId, event.title, event.description, event.price, event.amount)
    }

    @StateTransitionFunc
    fun deleteItemFromCatalog(event: ItemRemovedEvent) {
        items.remove(event.itemId)
    }

    @StateTransitionFunc
    fun changeItemPrice(event: ItemPriceChangedEvent) {
        items[event.itemId]!!.price = event.itemPrice
    }

    @StateTransitionFunc
    fun itemRefiled(event: ItemRefiledEvent) {
        items[event.itemId]!!.amount += event.amount
    }

    @StateTransitionFunc
    fun itemSold(event: ItemSoldEvent) {
        items[event.itemId]!!.amount -= event.amount
    }

    @StateTransitionFunc
    fun itemSoldOut(event: ItemSoldOutEvent) {
        items[event.itemId]!!.amount = 0
    }
}

data class Item(
    val id: UUID,
    val title: String,
    val description: String,
    var price: Int,
    var amount: Int
)