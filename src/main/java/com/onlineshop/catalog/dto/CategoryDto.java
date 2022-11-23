package com.onlineshop.catalog.dto;

import java.util.List;
import java.util.UUID;

public record CategoryDto(
        UUID id,
        String typeName,
        List<ItemDto> itemDtoList
) {
}
