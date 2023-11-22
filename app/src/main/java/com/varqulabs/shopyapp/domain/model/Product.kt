package com.varqulabs.shopyapp.domain.model

data class Product(
    val id: String,
    val name: String,
    val description: String,
    val category: String,
    val price: Double,
    val ratingScore: Double,
    val ratingCount: Int,
    val imageUrl: String

)
