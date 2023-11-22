package com.varqulabs.shopyapp.data.repository

import com.varqulabs.shopyapp.data.mapper.toDomain
import com.varqulabs.shopyapp.data.remote.FakeStoreApi
import com.varqulabs.shopyapp.data.remote.util.resultOf
import com.varqulabs.shopyapp.domain.model.Product
import com.varqulabs.shopyapp.domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import java.util.concurrent.CancellationException


class HomeRepositoryImpl(
    private val api: FakeStoreApi
) : HomeRepository {
    override fun getAllProducts(): Flow<Result<List<Product>>> {
        return flow {
            try {
                val result = api.getAllProducts()
                val resultMapped = result.map { it.toDomain() }
                emit(Result.success(resultMapped))
            } catch (e: Exception) {
                emit(Result.failure(e))
            }
        }.onStart {
            emit(Result.success(emptyList()))
        }


    }

    override fun getProductDetail(id: Int): Flow<Result<Product>> {
        return flow {
            try {
                val result = api.getProductDetail(id)
                val resultMapped = result.toDomain()
                emit(Result.success(resultMapped))
            } catch (e: Exception) {
                emit(Result.failure(e))
            }
        }.onStart {
            emit(
                Result.success(
                    Product(
                        "404",
                        "No name",
                        "No description",
                        "404",
                        price = 404.0,
                        ratingCount = 404,
                        ratingScore = 4.04,
                        imageUrl = ""
                    )
                )
            )
        }
    }
}