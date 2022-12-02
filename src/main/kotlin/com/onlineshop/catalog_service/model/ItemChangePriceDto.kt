package com.onlineshop.catalog_service.model

import java.util.*

data class ItemChangePriceDto (
    val id : UUID,
    val price : Int
    ){}