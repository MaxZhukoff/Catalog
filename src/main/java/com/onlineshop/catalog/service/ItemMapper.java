package com.onlineshop.catalog.service;

import com.onlineshop.catalog.dto.ItemCreateDto;
import com.onlineshop.catalog.entity.CategoryEntity;
import com.onlineshop.catalog.entity.ItemEntity;
import com.onlineshop.catalog.dto.ItemDto;
import com.onlineshop.catalog.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

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
                model.category());
    }

    //пофиксить поиск категорий(CatalogService.createItem)
    public ItemEntity itemCreateDtoToEntity(ItemCreateDto model, CategoryEntity category) {
//        CategoryEntity category = Optional.ofNullable(model.categoryId())
//                .map(category_id -> categoryRepository
//                        .findById(UUID.fromString(category_id))
//                        .orElseThrow(
//                                () -> new IllegalArgumentException("Category with id " + model.categoryId() + " not found")
//                        )
//                ).orElse(null);

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
