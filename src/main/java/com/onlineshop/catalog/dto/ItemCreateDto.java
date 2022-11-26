package com.onlineshop.catalog.dto;

public record ItemCreateDto(
    String name,
    String description,
    long price,
    long amount
){
}
