package com.varqulabs.shopyapp.data.remote.dto

import com.squareup.moshi.Json

data class StoreProductDto(
    @Json(name = "category")
    val category: String,
    @Json(name = "description")
    val description: String,
    @Json(name = "id")
    val id: Int,
    @Json(name = "image")
    val image: String,
    @Json(name = "price")
    val price: Double,
    @Json(name = "rating")
    val rating: Rating,
    @Json(name = "title")
    val title: String
)