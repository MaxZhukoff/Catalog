package com.onlineshop.catalog.controller;

import com.onlineshop.catalog.dto.ItemAmountChangeDto;
import com.onlineshop.catalog.dto.ItemCreateDto;
import com.onlineshop.catalog.dto.ItemDto;
import com.onlineshop.catalog.dto.ItemPriceChangeDto;
import com.onlineshop.catalog.service.ItemService;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/catalog/items")
public class ItemController {

    private final ItemService itemService;

    @GetMapping
    public List<ItemDto> getAllItems() {
        return itemService.getAllItems();
    }

    @PostMapping
    public ItemDto createItem(@RequestBody @Validated ItemCreateDto item) {
        return itemService.createItem(item);
    }

    @GetMapping("/{id}")
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
    }

}
