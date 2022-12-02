package ru.onlineshop.catalog.api

import ru.quipy.core.annotations.DomainEvent
import ru.quipy.domain.Event
import java.util.UUID

const val ITEM_ADDED_TO_THE_CATALOG = "ITEM_ADDED_TO_THE_CATALOG_EVENT"
const val CATALOG_CREATED_EVENT = "CATALOG_CREATED_EVENT"
const val ITEM_REMOVED_FROM_THE_CATALOG = "ITEM_REMOVED_FROM_THE_CATALOG_EVENT"
//const val ITEM_REFILED = "ITEM_REFILED_EVENT"
//const val ITEM_PRICE_CHANGED = "ITEM_PRICE_CHANGED_EVENT"
//const val ITEM_SOLD_OUT = "ITEM_SOLD_OUT_EVENT"

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

@DomainEvent(name = CATALOG_CREATED_EVENT)
data class CatalogCreateEvent(
    val catalogId: String = "catalog"
) : Event<CatalogAggregate>(
    name = CATALOG_CREATED_EVENT,
)

@DomainEvent(ITEM_REMOVED_FROM_THE_CATALOG)
data class ItemRemovedEvent(
    val itemId: UUID,
) : Event<CatalogAggregate>(
    name = ITEM_REMOVED_FROM_THE_CATALOG,
)

//@DomainEvent(ITEM_REFILED)
//data class ItemRefiledEvent(
//    val itemId: UUID,
//    val itemAmount: Int,
//) : Event<CatalogAggregate>(
//    name = ITEM_REFILED,
//)
//
//@DomainEvent(ITEM_PRICE_CHANGED)
//data class ItemPriceChangedEvent(
//    val itemId: UUID,
//    val itemPrice: Int,
//) : Event<CatalogAggregate>(
//    name = ITEM_PRICE_CHANGED,
//)
//
//@DomainEvent(ITEM_SOLD_OUT)
//data class ItemSoldOutEvent(
//    val itemId: UUID
//) : Event<CatalogAggregate>(
//    name = ITEM_SOLD_OUT,
//)
//