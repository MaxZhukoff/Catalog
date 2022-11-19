package com.onlineshop.catalog.service;

import com.onlineshop.catalog.dto.*;
import com.onlineshop.catalog.entity.CategoryEntity;
import com.onlineshop.catalog.entity.ItemEntity;
import com.onlineshop.catalog.repository.CategoryRepository;
import com.onlineshop.catalog.repository.ItemRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CatalogService {
    private final ItemRepository itemRepository;
    private final CategoryRepository categoryRepository;
    private final ItemMapper itemMapper;
    private final CategoryMapper categoryMapper;

    public List<ItemDto> getAllItems() {
        List<ItemDto> itemDtoList = new ArrayList<>();
        List<ItemEntity> itemEntities = itemRepository.findAll();

        for (ItemEntity catalog : itemEntities) {
            itemDtoList.add(itemMapper.entityToItemDto(catalog));
        }

        return itemDtoList;
    }

    public ItemDto createItem(ItemCreateDto itemCreateDto) {
        ItemEntity itemEntity;
        try {
            CategoryDto categoryDto = getCategoryById(UUID.fromString(itemCreateDto.categoryId()));
            itemEntity = itemMapper.itemCreateDtoToEntity(itemCreateDto, categoryMapper.dtoToEntity(categoryDto));
            final ItemEntity createdItem = itemRepository.saveAndFlush(itemEntity);
            return itemMapper.entityToItemDto(createdItem);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    public CategoryDto createCategory(CategoryCreateDto dto) {
        CategoryEntity categoryEntity;
        try {
            categoryEntity = categoryMapper.createDtoToEntity(dto);
            final CategoryEntity createdCategory = categoryRepository.saveAndFlush(categoryEntity);
            return categoryMapper.entityToModel(createdCategory);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    public List<CategoryDto> getAllCategories() {
        List<CategoryDto> categoryDtoList = new ArrayList<>();
        List<CategoryEntity> categoryEntities = categoryRepository.findAll();

        for (CategoryEntity catalog : categoryEntities) {
            categoryDtoList.add(categoryMapper.entityToModel(catalog));
        }

        return categoryDtoList;
    }

    public ItemDto getItemByID(UUID id) {
        ItemEntity itemEntity = getItemEntity(id);

        return itemMapper.entityToItemDto(itemEntity);
    }

    public CategoryDto getCategoryById(UUID id) {
        Optional<CategoryEntity> optionalCategoryEntity = categoryRepository.findById(id);

        if (optionalCategoryEntity.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } else {
            return categoryMapper.entityToModel(optionalCategoryEntity.get());
        }
    }

    public void deleteItemById(UUID id) {
        try {
            itemRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "A item with this id:" + id + " was not found");
        }
    }
    @Transactional
    public ItemDto changePriceOfItemById(UUID id, ItemPriceChangeDto dto) {
        ItemEntity itemEntity = getItemEntity(id);

        itemEntity.setPrice(dto.price());
        itemRepository.save(itemEntity);

        return itemMapper.entityToItemDto(itemEntity);
    }

    @Transactional
    public ItemDto changeAmountOfItemById(UUID id, ItemAmountChangeDto dto) {
        ItemEntity itemEntity = getItemEntity(id);

        itemEntity.setAmount(dto.amount());
        itemRepository.save(itemEntity);

        return itemMapper.entityToItemDto(itemEntity);
    }

    private ItemEntity getItemEntity(UUID id) {
        Optional<ItemEntity> optionalItemEntity = itemRepository.findById(id);

        if (optionalItemEntity.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } else {
            return optionalItemEntity.get();
        }
    }

}