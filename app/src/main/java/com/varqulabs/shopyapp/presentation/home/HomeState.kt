package com.varqulabs.shopyapp.presentation.home

import com.varqulabs.shopyapp.domain.model.Product

data class HomeState(
    val isLoading: Boolean = false,
    val errorMsg: String? = null,
    val products: List<Product> = emptyList(),
    val isExpanded: Boolean = false
)
