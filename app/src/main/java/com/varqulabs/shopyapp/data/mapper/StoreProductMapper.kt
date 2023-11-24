package com.varqulabs.shopyapp.data.mapper

import com.varqulabs.shopyapp.data.local.entity.ProductEntity
import com.varqulabs.shopyapp.data.remote.dto.StoreProductDto
import com.varqulabs.shopyapp.domain.model.Product

fun StoreProductDto.toDomain(): Product {
    return Product(
        id = this.id.toString(),
        name = this.title,
        description = this.description,
        category = this.category,
        price = this.price,
        ratingScore = this.rating.rate,
        ratingCount = this.rating.count,
        imageUrl = this.image
    )
}

fun List<ProductEntity>.toDomain(): List<Product> {
    return map { it.toDomain() }
}

fun Product.toEntity(): ProductEntity{
    return ProductEntity(
        id = this.id,
        name = this.name,
        description = this.description,
        category = this.category,
        price = this.price,
        ratingScore = this.ratingScore,
        ratingCount = this.ratingCount,
        imageUrl = this.imageUrl
    )
}

fun ProductEntity.toDomain(): Product{
    return Product(
        id = this.id,
        name = this.name,
        description = this.description,
        category = this.category,
        price = this.price,
        ratingScore = this.ratingScore,
        ratingCount = this.ratingCount,
        imageUrl = this.imageUrl
    )
}