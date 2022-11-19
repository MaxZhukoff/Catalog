package com.onlineshop.catalog.service;

import com.onlineshop.catalog.dto.*;
import com.onlineshop.catalog.entity.CategoryEntity;
import com.onlineshop.catalog.entity.ItemEntity;
import com.onlineshop.catalog.repository.CategoryRepository;
import com.onlineshop.catalog.repository.ItemRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CategoryService {
    private final ItemRepository itemRepository;
    private final CategoryRepository categoryRepository;
    private final ItemMapper itemMapper;
    private final CategoryMapper categoryMapper;

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

    public CategoryDto getCategoryDtoById(UUID id) {

        Optional<CategoryEntity> optionalCategoryEntity = categoryRepository.findById(id);

        if (optionalCategoryEntity.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } else {
            return categoryMapper.entityToModel(optionalCategoryEntity.get());
        }
    }

    public CategoryEntity getCategoryEntityById(UUID id) {

        Optional<CategoryEntity> optionalCategoryEntity = categoryRepository.findById(id);

        if (optionalCategoryEntity.isEmpty()) {

            throw new IllegalArgumentException("Incorrect category id : " + id);
        } else {

            return optionalCategoryEntity.get();
        }
    }

}