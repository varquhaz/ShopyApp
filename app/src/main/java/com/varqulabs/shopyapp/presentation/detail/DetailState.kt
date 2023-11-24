package com.varqulabs.shopyapp.presentation.detail

import com.varqulabs.shopyapp.domain.model.Product

data class DetailState(
    val selectedProductId: String? = null,
    val product: Product? = null,
    val errorMsg: String? = null,
    val isLoading: Boolean = false,
    val isSharing: Boolean = false
)
