package com.onlineshop.catalog_service.controller

import com.onlineshop.catalog_service.api.*
import com.onlineshop.catalog_service.logic.Catalog
import com.onlineshop.catalog_service.model.CatalogItemDto
import com.onlineshop.catalog_service.model.ItemChangeAmountDto
import com.onlineshop.catalog_service.model.ItemChangePriceDto
import org.springframework.web.bind.annotation.*
import ru.quipy.core.EventSourcingService
import java.util.*

@RestController
@RequestMapping("/catalog")
class CatalogController(
    val catalogEsService: EventSourcingService<UUID, CatalogAggregate, Catalog>
) {
    @PostMapping("/{catalogId}/{itemId}")
    fun addItemToCatalog (@RequestBody item : CatalogItemDto) : ItemAddedEvent{
        return catalogEsService.create { it.addItemToCatalog(item.id, item.title, item.description, item.amount, item.price, item.category) }
    }

    @DeleteMapping("/{catalogId}/{itemId}")
    fun deleteItemFromCatalog (@PathVariable  catalogId : UUID, @PathVariable itemId : UUID) : ItemRemovedEvent{
        return catalogEsService.update(catalogId) { it.deleteIttemFromCatalog(itemId) }
    }

    @PutMapping("/{catalogId}/{ItemId}")
    fun updateItemPrice(@PathVariable catalogId : UUID, @RequestBody item : ItemChangePriceDto) : ItemPriceChangedEvent{
        return catalogEsService.update(catalogId) { it.changeItemPrice(item.id, item.price)}
    }

    @PutMapping("{/{catalogId}/ItemId}")
    fun updateItemAmount(@PathVariable catalogId : UUID, @RequestBody item : ItemChangeAmountDto) : ItemRefiledEvent{
        return catalogEsService.update(catalogId) {it.changeItemAmount(item.id, item.amount)}
    }

    //TODO можно еще добавить геты + я не уверен правильная ли это реализация, если делать геты, то надо добавлять итемы в мапу, хотя Андрей не добавлял нигде в примере, тут хз
}