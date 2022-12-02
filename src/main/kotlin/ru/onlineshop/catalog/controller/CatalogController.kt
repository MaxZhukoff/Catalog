package ru.onlineshop.catalog.controller

import ru.onlineshop.catalog.logic.CatalogAggregateState
import org.springframework.web.bind.annotation.*
import ru.onlineshop.catalog.api.*
import ru.quipy.core.EventSourcingService
import java.util.*

@RestController
@RequestMapping("/catalog")
class CatalogController(
    val catalogEsService: EventSourcingService<UUID, CatalogAggregate, CatalogAggregateState>
) {
//    @PostMapping("/{catalogId}/{itemId}")
//    fun addItemToCatalog (@RequestBody item : CatalogItemDto) : ItemAddedEvent {
//        return catalogEsService.create { it.addItemToCatalog(item.id, item.title, item.description, item.amount, item.price, item.category) }
//    }
//
//    @DeleteMapping("/{catalogId}/{itemId}")
//    fun deleteItemFromCatalog (@PathVariable  catalogId : UUID, @PathVariable itemId : UUID) : ItemRemovedEvent {
//        return catalogEsService.update(catalogId) { it.deleteItemFromCatalog(itemId) }
//    }
//
//    @PutMapping("/{catalogId}/{ItemId}")
//    fun updateItemPrice(@PathVariable catalogId : UUID, @RequestBody item : ItemChangePriceDto) : ItemPriceChangedEvent {
//        return catalogEsService.update(catalogId) { it.changeItemPrice(item.id, item.price)}
//    }
//
//    @PutMapping("{/{catalogId}/ItemId}")
//    fun updateItemAmount(@PathVariable catalogId : UUID, @RequestBody item : ItemChangeAmountDto) : ItemRefiledEvent {
//        return catalogEsService.update(catalogId) {it.changeItemAmount(item.id, item.amount)}
//    }

    //TODO можно еще добавить геты + я не уверен правильная ли это реализация, если делать геты, то надо добавлять итемы в мапу, хотя Андрей не добавлял нигде в примере, тут хз
}