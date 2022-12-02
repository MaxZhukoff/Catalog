package ru.onlineshop.catalog.model

import java.util.*

data class ItemCreateDto(
    val title: String,
    val description: String,
    val price: Int = 100,
    val amount: Int
)
