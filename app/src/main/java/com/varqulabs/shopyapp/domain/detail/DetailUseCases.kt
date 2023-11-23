package com.varqulabs.shopyapp.domain.detail

import com.varqulabs.shopyapp.domain.detail.usecases.GetProductDetailAlphaUseCase
import com.varqulabs.shopyapp.domain.detail.usecases.GetProductDetailUseCase

data class DetailUseCases(
    val getProductDetailUseCase: GetProductDetailUseCase,
    val getProductDetailAlphaUseCase: GetProductDetailAlphaUseCase
)