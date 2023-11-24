package com.varqulabs.shopyapp.data.repository

import com.varqulabs.shopyapp.data.local.HomeDao
import com.varqulabs.shopyapp.data.mapper.toDomain
import com.varqulabs.shopyapp.data.mapper.toEntity
import com.varqulabs.shopyapp.data.remote.FakeStoreApi
import com.varqulabs.shopyapp.data.remote.util.resultOf
import com.varqulabs.shopyapp.domain.model.Product
import com.varqulabs.shopyapp.domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart

class HomeRepositoryImpl(
    private val dao: HomeDao,
    private val api: FakeStoreApi
) : HomeRepository {
    override fun getAllProducts(): Flow<List<Product>> {
        val localFlow = dao.getAllProducts().map {
            it.map { it.toDomain() }
        }
        val apiFlow = getProductsFromApi()

        return localFlow.combine(apiFlow){ db, _ ->
            db
        }
    }

    override suspend fun getProductDetail(id: String): Result<Product> {
        return try {
                val result = dao.getProductById(id)
                val resultMapped = result.toDomain()
                Result.success(resultMapped)
            } catch (e: Exception){
                Result.failure(e)
            }
    }

    private fun getProductsFromApi(): Flow<List<Product>> {
        return flow {
            resultOf {
                val products = api.getAllProducts().map {
                    it.toDomain()
                }
                insertProducts(products = products)
            }
            emit(emptyList<Product>())
        }.onStart {
            emit(emptyList())
        }
    }

    private suspend fun insertProducts(products: List<Product>) {
        products.forEach {
            dao.upsertProduct(it.toEntity())
        }
    }

}