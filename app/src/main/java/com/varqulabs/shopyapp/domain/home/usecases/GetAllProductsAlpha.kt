package com.varqulabs.shopyapp.domain.home.usecases

import com.varqulabs.shopyapp.domain.model.Product
import com.varqulabs.shopyapp.domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow

class GetAllProductsAlpha(
    private val repository: HomeRepository
) {

    operator fun invoke(): Flow<List<Product>> {
        return repository.getAllProductsAlpha()
    }

}