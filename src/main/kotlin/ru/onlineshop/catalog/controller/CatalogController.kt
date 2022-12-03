package ru.onlineshop.catalog.controller

import ru.onlineshop.catalog.logic.CatalogAggregateState
import org.springframework.web.bind.annotation.*
import ru.onlineshop.catalog.api.*
import ru.onlineshop.catalog.logic.Item
import ru.onlineshop.catalog.model.ItemCreateDto
import ru.quipy.core.EventSourcingService
import ru.quipy.domain.Event
import java.util.*

@RestController
class CatalogController(
    val catalogEsService: EventSourcingService<String, CatalogAggregate, CatalogAggregateState>
) {
    @PostMapping("/_internal/catalog")
    fun createCatalog(): CatalogCreateEvent {
        return catalogEsService.create {
            it.createCatalog()
        }
    }

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
        return catalogEsService.update("catalog") {
            it.addItemToCatalog(
                title = dto.title,
                description = dto.description,
                price = dto.price,
                amount = dto.amount
            )
        }
    }

    @DeleteMapping("/_internal/catalogItem/{itemId}")
    fun deleteItem(@PathVariable itemId: UUID) {
        catalogEsService.update("catalog") {
            it.deleteItemFromCatalog(itemId)
        }
    }

    @PatchMapping("/_internal/catalogItem/{itemId}/price")
    fun changePriceItem(@PathVariable itemId: UUID, @RequestParam newPrice: Int): ItemPriceChangedEvent? {
        return catalogEsService.update("catalog") {
            it.changeItemPrice(itemId, newPrice)
        }
    }

    @PatchMapping("/_internal/catalogItem/{itemId}/refill")
    fun refillItem(@PathVariable itemId: UUID, @RequestParam amountForRefill: Int): ItemRefiledEvent? {
        return catalogEsService.update("catalog") {
            it.refillItem(itemId, amountForRefill)
        }
    }

    @PatchMapping("/_internal/catalogItem/{itemId}/sell")
    fun sellItem(@PathVariable itemId: UUID, @RequestParam amountForSell: Int): Event<CatalogAggregate>? {
        return catalogEsService.update("catalog") {
            it.sellItem(itemId, amountForSell)
        }
    }
}
