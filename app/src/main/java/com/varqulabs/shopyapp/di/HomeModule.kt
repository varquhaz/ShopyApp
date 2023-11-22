package com.varqulabs.shopyapp.di

import com.varqulabs.shopyapp.data.remote.FakeStoreApi
import com.varqulabs.shopyapp.data.remote.util.Constants
import com.varqulabs.shopyapp.data.repository.HomeRepositoryImpl
import com.varqulabs.shopyapp.domain.detail.DetailUseCases
import com.varqulabs.shopyapp.domain.detail.usecases.GetProductDetailUseCase
import com.varqulabs.shopyapp.domain.home.HomeUseCases
import com.varqulabs.shopyapp.domain.home.usecases.GetAllProductsUseCase
import com.varqulabs.shopyapp.domain.repository.HomeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HomeModule {

    // API
    @Singleton
    @Provides
    fun provideHomeApi(): FakeStoreApi {
        return Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build().create(FakeStoreApi::class.java)
    }

    // Home Use Cases
    @Singleton
    @Provides
    fun provideHomeUseCases(
        repository: HomeRepository
    ) : HomeUseCases {
        return HomeUseCases(
            getAllProductsUseCase = GetAllProductsUseCase(repository)
        )
    }

    //Detail Use Cases
    @Singleton
    @Provides
    fun provideDetailUseCases(
        repository: HomeRepository
    ): DetailUseCases{
        return DetailUseCases(
            getProductDetailUseCase = GetProductDetailUseCase(repository)
        )
    }


    // Provide Home Repository
    @Singleton
    @Provides
    fun provideHomeRepository(
        api: FakeStoreApi
    ): HomeRepository{
        return HomeRepositoryImpl(api)
    }


}