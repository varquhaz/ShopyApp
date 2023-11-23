package com.varqulabs.shopyapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.varqulabs.shopyapp.data.remote.dto.Rating

@Entity
data class ProductEntity(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val name: String,
    val description: String,
    val category: String,
    val price: Double,
    val ratingScore: Double,
    val ratingCount: Int,
    val imageUrl: String
)

