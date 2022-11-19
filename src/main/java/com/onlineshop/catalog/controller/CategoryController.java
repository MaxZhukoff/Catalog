package com.onlineshop.catalog.controller;

import com.onlineshop.catalog.dto.CategoryCreateDto;
import com.onlineshop.catalog.dto.CategoryDto;
import com.onlineshop.catalog.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/catalog")
public class CategoryController {

    private final CategoryService catalogService;

    @PostMapping("/category")
    public CategoryDto createCategory(@RequestBody @Validated CategoryCreateDto dto) {
        return catalogService.createCategory(dto);
    }

    @GetMapping("/categories")
    public List<CategoryDto> getAllCategories() {
        return catalogService.getAllCategories();
    }

    @GetMapping("/category/{id}")
    public CategoryDto getCategoryById(@PathVariable UUID id) {
        return catalogService.getCategoryDtoById(id);
    }
}
