package com.onlineshop.catalog.dto;

import java.util.UUID;

public record ItemDto(
        UUID id,
        String name,
        String description,
        int price,
        int amount
) {
}
