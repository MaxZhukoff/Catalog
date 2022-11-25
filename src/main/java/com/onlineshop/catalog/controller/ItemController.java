package com.onlineshop.catalog.controller;

import com.onlineshop.catalog.api.ItemAggregate;
import com.onlineshop.catalog.api.events.CreatedItemEvent;
import com.onlineshop.catalog.dto.ItemAmountChangeDto;
import com.onlineshop.catalog.dto.ItemCreateDto;
import com.onlineshop.catalog.dto.ItemDto;
import com.onlineshop.catalog.dto.ItemPriceChangeDto;
import com.onlineshop.catalog.logic.Item;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.quipy.core.EventSourcingService;

import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/catalog/items")
public class ItemController {

    private final EventSourcingService<UUID, ItemAggregate, Item> accountEsService;

    @PostMapping
    public CreatedItemEvent createItem(@RequestBody @Validated ItemCreateDto item) {

        return accountEsService.create(x -> x.createItem(item.name(), item.description(), item.price(), item.amount()));
    }

    @GetMapping("/{itemId}")
    public Item getItem(@PathVariable("itemId") UUID itemId) {

        return accountEsService.getState(itemId);
    }

/*    @GetMapping("/{id}")
    public ItemDto getItemById(@PathVariable UUID id) {
        return itemService.getItemByID(id);
    }

    @DeleteMapping("/{id}")
    public void deleteItemById(@PathVariable UUID id) {
        itemService.deleteItemById(id);
    }

    @PutMapping("/{id}/price")
    public ItemDto changePriceOfItemById(@RequestBody @Validated ItemPriceChangeDto dto,
                                         @PathVariable("id") UUID id) {

        return itemService.changePriceOfItemById(id, dto);
    }

    @PutMapping("/{id}/amount")
    public ItemDto changeAmountOfItemById(@RequestBody @Validated ItemAmountChangeDto dto,
                                          @PathVariable("id") UUID id) {

        return itemService.changeAmountOfItemById(id, dto);
    }*/

}
