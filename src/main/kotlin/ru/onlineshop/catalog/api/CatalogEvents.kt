package ru.onlineshop.catalog.api

import ru.quipy.core.annotations.DomainEvent
import ru.quipy.domain.Event
import java.util.UUID

const val CATALOG_CREATED = "CATALOG_CREATED_EVENT"
const val ITEM_ADDED_TO_THE_CATALOG = "ITEM_ADDED_TO_THE_CATALOG_EVENT"
const val ITEM_REMOVED_FROM_THE_CATALOG = "ITEM_REMOVED_FROM_THE_CATALOG_EVENT"
const val ITEM_PRICE_CHANGED = "ITEM_PRICE_CHANGED_EVENT"
const val ITEM_AMOUNT_CHANGED = "ITEM_AMOUNT_CHANGED"
const val ITEM_SOLD_OUT = "ITEM_SOLD_OUT_EVENT"
const val ITEM_REFILED = "ITEM_REFILED_EVENT"

@DomainEvent(name = ITEM_ADDED_TO_THE_CATALOG)
data class ItemAddedEvent(
    val itemId: UUID,
    val title: String,
    val description: String,
    val price: Int,
    val amount: Int
) : Event<CatalogAggregate>(
    name = ITEM_ADDED_TO_THE_CATALOG,
)

@DomainEvent(name = CATALOG_CREATED)
data class CatalogCreateEvent(
    val catalogId: String = "catalog"
) : Event<CatalogAggregate>(
    name = CATALOG_CREATED,
)

@DomainEvent(ITEM_REMOVED_FROM_THE_CATALOG)
data class ItemRemovedEvent(
    val itemId: UUID,
) : Event<CatalogAggregate>(
    name = ITEM_REMOVED_FROM_THE_CATALOG,
)

@DomainEvent(ITEM_PRICE_CHANGED)
data class ItemPriceChangedEvent(
    val itemId: UUID,
    val itemPrice: Int,
) : Event<CatalogAggregate>(
    name = ITEM_PRICE_CHANGED,
)

@DomainEvent(ITEM_AMOUNT_CHANGED)
data class ItemAmountChangedEvent(
    val itemId: UUID,
    val amountChangeTo: Int,
) : Event<CatalogAggregate>(
    name = ITEM_AMOUNT_CHANGED,
)

@DomainEvent(ITEM_SOLD_OUT)
data class ItemSoldOutEvent(
    val itemId: UUID
) : Event<CatalogAggregate>(
    name = ITEM_SOLD_OUT,
)

@DomainEvent(ITEM_REFILED)
data class ItemRefiledEvent(
    val itemId: UUID,
    val amount: Int
) : Event<CatalogAggregate>(
    name = ITEM_REFILED,
)
