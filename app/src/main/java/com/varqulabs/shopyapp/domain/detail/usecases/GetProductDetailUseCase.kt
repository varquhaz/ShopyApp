package com.varqulabs.shopyapp.domain.detail.usecases

import com.varqulabs.shopyapp.domain.model.Product
import com.varqulabs.shopyapp.domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow

class GetProductDetailUseCase(
    private val repository: HomeRepository
) {

    suspend operator fun invoke(id: String): Result<Product> {
        return repository.getProductDetail(id)
    }

}