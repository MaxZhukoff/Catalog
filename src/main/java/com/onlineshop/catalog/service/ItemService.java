package com.onlineshop.catalog.service;

import com.onlineshop.catalog.dto.*;
import com.onlineshop.catalog.entity.ItemEntity;
import com.onlineshop.catalog.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ItemService {
    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;

    public ItemDto createItem(ItemCreateDto itemCreateDto) throws IllegalArgumentException {

        ItemEntity itemEntity = itemMapper.itemCreateDtoToEntity(itemCreateDto);

        final ItemEntity createdItem = itemRepository.saveAndFlush(itemEntity);

        return itemMapper.entityToItemDto(createdItem);
    }

    public List<ItemDto> getAllItems() {

        List<ItemDto> itemDtoList = new ArrayList<>();

        List<ItemEntity> itemEntities = itemRepository.findAll();

        for (ItemEntity catalog : itemEntities) {
            itemDtoList.add(itemMapper.entityToItemDto(catalog));
        }

        return itemDtoList;
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

    public ItemDto getItemByID(UUID id) {

        ItemEntity itemEntity = getItemEntity(id);

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
