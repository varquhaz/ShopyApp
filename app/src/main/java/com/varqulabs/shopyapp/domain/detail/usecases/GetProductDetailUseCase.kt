package com.varqulabs.shopyapp.domain.detail.usecases

import com.varqulabs.shopyapp.domain.model.Product
import com.varqulabs.shopyapp.domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow

class GetProductDetailUseCase(
    private val repository: HomeRepository
) {

    operator fun invoke(id: Int): Flow<Result<Product>>{
        return repository.getProductDetail(id)
    }

}