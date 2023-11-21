package com.varqulabs.shopyapp.presentation.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.varqulabs.shopyapp.domain.home.HomeUseCases
import com.varqulabs.shopyapp.domain.home.usecases.GetAllProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeUseCases: HomeUseCases
) : ViewModel() {

    var state by mutableStateOf(HomeState())
        private set

    init{
        getAllProducts()
    }

    private fun getAllProducts(){
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            homeUseCases.getAllProductsUseCase().collectLatest {
                it.onSuccess {
                    state = state.copy(
                        products = it
                    )
                }.onFailure {
                    state = state.copy(
                        errorMsg = it.message
                    )
                }
            }
            state = state.copy(isLoading = false)
        }
    }




}