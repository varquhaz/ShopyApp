package com.varqulabs.shopyapp.domain.repository

import com.varqulabs.shopyapp.data.remote.dto.StoreProductDto
import com.varqulabs.shopyapp.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface HomeRepository {

    fun getAllProducts() : Flow<Result<List<Product>>>

    fun getProductDetail(id: Int): Flow<Result<Product>>

}