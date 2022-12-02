package ru.onlineshop.catalog.model

import java.util.*

data class ItemChangeAmountDto(
    val id : UUID,
    val amount : Int,
) {}