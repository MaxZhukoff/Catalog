package ru.onlineshop.catalog.model

data class ItemCreateDto(
    val title: String,
    val description: String,
    val price: Int = 100,
    val amount: Int
)
