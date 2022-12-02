package ru.onlineshop.catalog.controller

import ru.onlineshop.catalog.logic.CatalogAggregateState
import org.springframework.web.bind.annotation.*
import ru.onlineshop.catalog.api.*
import ru.onlineshop.catalog.logic.Item
import ru.onlineshop.catalog.model.CatalogItemDto
import ru.onlineshop.catalog.model.ItemCreateDto
import ru.quipy.core.EventSourcingService
import java.util.*

@RestController
class CatalogController(
    val catalogEsService: EventSourcingService<String, CatalogAggregate, CatalogAggregateState>
) {

    @GetMapping("/items/{itemId}")
    fun getItem(@PathVariable itemId: UUID): Item? {
        return catalogEsService.getState("catalog")!!.items[itemId]
    }

    @GetMapping("/items")
    fun getAllItems(): List<Item>? {
        return catalogEsService.getState("catalog")!!.items.values.toList()
    }

    @PostMapping("/_internal/catalogItem")
    fun addItem(@RequestBody dto: ItemCreateDto): ItemAddedEvent {
        return catalogEsService.create {
            it.addItemToCatalog(
                title = dto.title,
                description = dto.description,
                price = dto.price,
                amount = dto.amount
            )
        }
    }


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