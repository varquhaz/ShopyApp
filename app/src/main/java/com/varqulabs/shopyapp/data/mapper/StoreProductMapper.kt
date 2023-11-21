package com.varqulabs.shopyapp.data.mapper

import com.varqulabs.shopyapp.data.remote.dto.StoreProductDto
import com.varqulabs.shopyapp.domain.model.Product

fun StoreProductDto.toDomain(): Product {
    return Product(
        id = this.id,
        name = this.title,
        description = this.description,
        category = this.category,
        price = this.price,
        ratingScore = this.rating.rate,
        ratingCount = this.rating.count,
        imageUrl = this.image
    )
}