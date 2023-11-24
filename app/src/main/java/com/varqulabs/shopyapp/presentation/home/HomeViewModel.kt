package com.varqulabs.shopyapp.presentation.home

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.varqulabs.shopyapp.domain.home.HomeUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
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
                state = state.copy(
                    products = it,
                    isLoading = false
                )
            }
        }
    }

    fun onEvent(event:HomeEvent){

        when(event){
            is HomeEvent.ReloadNetwork -> {
                getAllProducts()
            }
        }

    }




}