package com.onlineshop.catalog.dto;

import com.onlineshop.catalog.entity.CategoryEntity;

import java.util.UUID;

public record ItemDto(
        UUID id,
        String name,
        String description,
        int price,
        int amount,
        CategoryEntity category
) {
}
