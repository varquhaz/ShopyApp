package com.varqulabs.shopyapp.domain.repository

import com.varqulabs.shopyapp.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface HomeRepository {

    fun getAllProducts(): Flow<List<Product>>

    fun getProductDetail(id: String): Flow<Product>

    suspend fun insertProducts(products: List<Product>)

}