package ru.onlineshop.catalog.model

import java.util.*

data class CatalogItemDto (
    val id: UUID,
    val title: String,
    val description: String,
    val category : String,
    val price: Int = 100,
    val amount: Int
)