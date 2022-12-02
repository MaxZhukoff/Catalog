package ru.onlineshop.catalog.api

import ru.quipy.core.annotations.DomainEvent
import ru.quipy.domain.Event
import java.util.UUID

const val ITEM_ADDED_TO_THE_CATALOG = "ITEM_ADDED_TO_THE_CATALOG_EVENT"
//const val ITEM_REMOVED_FROM_THE_CATALOG = "ITEM_REMOVED_FROM_THE_CATALOG_EVENT"
//const val ITEM_REFILED = "ITEM_REFILED_EVENT"
//const val ITEM_PRICE_CHANGED = "ITEM_PRICE_CHANGED_EVENT"
//const val ITEM_SOLD_OUT = "ITEM_SOLD_OUT_EVENT"
//const val CATALOG_CREATED = "CATALOG_CREATED_EVENT"

@DomainEvent(name = ITEM_ADDED_TO_THE_CATALOG)
data class ItemAddedEvent(
    val itemId: UUID,
    val itemName: String,
    val itemDescription: String,
    val itemPrice: Int,
    val itemAmount: Int,
    val itemCategory: String,
) : Event<CatalogAggregate>(
    name = ITEM_ADDED_TO_THE_CATALOG,
)

//@DomainEvent(ITEM_REMOVED_FROM_THE_CATALOG)
//data class ItemRemovedEvent(
//    val itemId: UUID,
//) : Event<CatalogAggregate>(
//    name = ITEM_REMOVED_FROM_THE_CATALOG,
//)
//
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
//@DomainEvent(CATALOG_CREATED)
//data class CatalogCreatedEvent(
//    val catalogId: UUID,
//) : Event<CatalogAggregate>(
//    name = CATALOG_CREATED,
//)