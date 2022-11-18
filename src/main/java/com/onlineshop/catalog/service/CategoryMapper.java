package com.onlineshop.catalog.service;

import com.onlineshop.catalog.dto.CategoryCreateDto;
import com.onlineshop.catalog.entity.CategoryEntity;
import com.onlineshop.catalog.dto.CategoryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@RequiredArgsConstructor
public class CategoryMapper {
    private final ItemMapper itemMapper;

    public CategoryEntity dtoToEntity(CategoryDto model) {
        return new CategoryEntity(
                model.id(),
                model.typeName(),
                model.itemDtoList().stream().map(itemMapper::itemDtoToEntity).toList()
        );
    }

    public CategoryEntity createDtoToEntity(CategoryCreateDto model) {
        return CategoryEntity.builder()
                .typeName(model.typeName())
                .items(new ArrayList<>())
                .build();
    }

    public CategoryDto entityToModel(CategoryEntity entity) {
        return new CategoryDto(
                entity.getId(),
                entity.getTypeName(),
                entity.getItems().stream().map(itemMapper::entityToItemDto).toList()
        );
    }
}
