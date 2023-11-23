package com.varqulabs.shopyapp.domain.detail.usecases

import com.varqulabs.shopyapp.domain.model.Product
import com.varqulabs.shopyapp.domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow

class GetProductDetailAlphaUseCase(
    private val repository: HomeRepository
) {

    operator fun invoke(id: String): Flow<Product> {
        return repository.getProductDetailAlpha(id)
    }

}