package com.onlineshop.catalog.dto;

public record ItemCreateDto(
    String name,
    String description,
    int price,
    String categoryId
){
}
