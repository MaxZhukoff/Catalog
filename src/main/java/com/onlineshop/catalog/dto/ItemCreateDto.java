package com.onlineshop.catalog.dto;

public record ItemCreateDto(
    String name,
    String description,
    double price,
    double amount
){
}
