package com.onlineshop.catalog_service.model

import java.util.*

data class ItemChangeAmountDto(
    val id : UUID,
    val amount : Int,
) {}