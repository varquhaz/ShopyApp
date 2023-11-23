package com.varqulabs.shopyapp.domain.home

import com.varqulabs.shopyapp.domain.home.usecases.GetAllProductsAlpha
import com.varqulabs.shopyapp.domain.home.usecases.GetAllProductsUseCase

data class HomeUseCases(
    val getAllProductsUseCase: GetAllProductsUseCase,
    val getAllProductsAlpha: GetAllProductsAlpha
)
