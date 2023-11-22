package com.varqulabs.shopyapp.data.remote

import com.varqulabs.shopyapp.data.remote.dto.StoreProductDto
import retrofit2.http.GET
import retrofit2.http.Path

interface FakeStoreApi {

    @GET("products")
    suspend fun getAllProducts(): List<StoreProductDto> // Response

    @GET("products/{id}")
    suspend fun getProductDetail(@Path("id") id: Int) : StoreProductDto


}