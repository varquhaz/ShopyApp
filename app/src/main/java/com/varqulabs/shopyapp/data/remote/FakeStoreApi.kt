package com.varqulabs.shopyapp.data.remote

import com.varqulabs.shopyapp.data.remote.dto.StoreProductDto
import retrofit2.http.GET

interface FakeStoreApi {

    @GET("products")
    suspend fun getAllProducts(): List<StoreProductDto> // Response


}