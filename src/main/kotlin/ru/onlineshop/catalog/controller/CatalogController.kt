package ru.onlineshop.catalog.controller


import jakarta.annotation.security.RolesAllowed
import org.springframework.web.bind.annotation.*
import ru.onlineshop.catalog.api.*
import ru.onlineshop.catalog.logic.CatalogAggregateState
import ru.onlineshop.catalog.logic.Item
import ru.onlineshop.catalog.model.ItemCreateDto
import ru.quipy.core.EventSourcingService
import ru.quipy.domain.Event
import java.util.*

@RestController
class CatalogController(
    val catalogEsService: EventSourcingService<String, CatalogAggregate, CatalogAggregateState>
) {

    @RolesAllowed("ADMIN")
    @PostMapping("/_internal/catalog")
    fun createCatalog(): CatalogCreateEvent {
        return catalogEsService.create {
            it.createCatalog()
        }
    }

    @RolesAllowed("USER", "ADMIN")
    @GetMapping("/items/{itemId}")
    fun getItem(@PathVariable itemId: UUID): Item? {

        return catalogEsService.getState("catalog")!!.items[itemId]
    }

    @RolesAllowed("USER", "ADMIN")
    @GetMapping("/items")
    fun getAllItems(): List<Item>? {
        return catalogEsService.getState("catalog")!!.items.values.toList()
    }

    @RolesAllowed("ADMIN")
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

    @RolesAllowed("ADMIN")
    @DeleteMapping("/_internal/catalogItem/{itemId}")
    fun deleteItem(@PathVariable itemId: UUID) {
        catalogEsService.update("catalog") {
            it.deleteItemFromCatalog(itemId)
        }
    }

    @RolesAllowed("ADMIN")
    @PatchMapping("/_internal/catalogItem/{itemId}/price")
    fun changePriceItem(@PathVariable itemId: UUID, @RequestParam newPrice: Int): ItemPriceChangedEvent? {
        return catalogEsService.update("catalog") {
            it.changeItemPrice(itemId, newPrice)
        }
    }

    @RolesAllowed("ADMIN")
    @PatchMapping("/_internal/catalogItem/{itemId}/refill")
    fun refillItem(@PathVariable itemId: UUID, @RequestParam amountForRefill: Int): ItemRefiledEvent? {
        return catalogEsService.update("catalog") {
            it.refillItem(itemId, amountForRefill)
        }
    }

    @RolesAllowed("ADMIN")
    @PatchMapping("/_internal/catalogItem/{itemId}/sell")
    fun sellItem(@PathVariable itemId: UUID, @RequestParam amountForSell: Int): Event<CatalogAggregate>? {
        return catalogEsService.update("catalog") {
            it.sellItem(itemId, amountForSell)
        }
    }
}
