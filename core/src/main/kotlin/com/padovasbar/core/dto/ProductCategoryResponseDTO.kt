package com.padovasbar.core.dto

import javax.persistence.Column

data class ProductCategoryResponseDTO(
    val productId: Long,
    var categoryId: Long,
    var categoryName: String,
    var name: String,
    var price: Double
)
