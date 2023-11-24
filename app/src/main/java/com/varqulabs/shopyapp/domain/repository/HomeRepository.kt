package com.varqulabs.shopyapp.domain.repository

import com.varqulabs.shopyapp.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface HomeRepository {

    fun getAllProducts(): Flow<List<Product>>

    suspend fun getProductDetail(id: String): Result<Product>

}