package com.onlineshop.catalog.service;

import com.onlineshop.catalog.dto.ItemCreateDto;
import com.onlineshop.catalog.entity.CategoryEntity;
import com.onlineshop.catalog.entity.ItemEntity;
import com.onlineshop.catalog.dto.ItemDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ItemMapper {

    public ItemEntity itemDtoToEntity(ItemDto model) {
        return new ItemEntity(
                model.id(),
                model.name(),
                model.description(),
                model.price(),
                model.amount(),
                model.category()
        );
    }

    public ItemEntity itemCreateDtoToEntity(ItemCreateDto model, CategoryEntity category) {
        return ItemEntity.builder()
                .name(model.name())
                .description(model.description())
                .price(model.price())
                .category(category)
                .build();
    }

    public ItemDto entityToItemDto(ItemEntity itemEntity) {
        return new ItemDto(
                itemEntity.getId(),
                itemEntity.getName(),
                itemEntity.getDescription(),
                itemEntity.getPrice(),
                itemEntity.getAmount(),
                itemEntity.getCategory());
    }
}
