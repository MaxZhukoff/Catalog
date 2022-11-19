package com.onlineshop.catalog.controller;

import com.onlineshop.catalog.dto.*;
import com.onlineshop.catalog.service.CatalogService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
public class CatalogController {
    private final CatalogService catalogService;

    public CatalogController(CatalogService catalogService) {
        this.catalogService = catalogService;
    }

    @GetMapping("/catalog/items")
    public List<ItemDto> getAllItems() {
        return catalogService.getAllItems();
    }

    @PostMapping("catalog/item")
    public ItemDto createItem(@RequestBody @Validated ItemCreateDto item) {
        return catalogService.createItem(item);
    }

    @PostMapping("catalog/category")
    public CategoryDto createCategory(@RequestBody @Validated CategoryCreateDto dto) {
        return catalogService.createCategory(dto);
    }

    @GetMapping("/catalog/categories")
    public List<CategoryDto> getAllCategories() {
        return catalogService.getAllCategories();
    }

    @GetMapping("/catalog/category/{id}")
    public CategoryDto getCategoryById(@PathVariable UUID id) {
        return catalogService.getCategoryById(id);
    }

    @GetMapping("/catalog/item/{id}")
    public ItemDto getItemById(@PathVariable UUID id) {
        return catalogService.getItemByID(id);
    }

    @DeleteMapping("/catalog/item/{id}")
    public void deleteItemById(@PathVariable UUID id) {
        catalogService.deleteItemById(id);
    }

    @PutMapping("/catalog/item/{id}/price")
    public ItemDto changePriceOfItemById(@RequestBody @Validated ItemPriceChangeDto dto, @PathVariable("id") UUID id) {
        return catalogService.changePriceOfItemById(id, dto);
    }

    @PutMapping("/catalog/item/{id}/amount")
    public ItemDto changeAmountOfItemById(@RequestBody @Validated ItemAmountChangeDto dto, @PathVariable("id") UUID id) {
        return catalogService.changeAmountOfItemById(id, dto);
    }

}