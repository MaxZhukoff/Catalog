package ru.onlineshop.catalog.model

import java.util.*

data class ItemChangePriceDto(
    val id: UUID,
    val price: Int
)