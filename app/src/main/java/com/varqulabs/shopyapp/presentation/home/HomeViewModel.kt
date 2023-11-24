package com.varqulabs.shopyapp.presentation.home

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.varqulabs.shopyapp.domain.home.HomeUseCases
import com.varqulabs.shopyapp.domain.model.Product
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

    private var copyProducts by
        mutableStateOf(emptyList<Product>())

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
                copyProducts = state.products
            }
        }
    }

    fun onEvent(event:HomeEvent){
        when(event){
            is HomeEvent.ReloadNetwork -> {
                getAllProducts()
            }
            is HomeEvent.SortByFilter -> {
                sortByFilter(event.sortFilter)
            }
            is HomeEvent.FilterByCategory -> {
                filterCategory(event.category)
            }
            else -> {}
        }
    }
    private fun sortByFilter(sortFilter: String){
        when(sortFilter){
            "Price Low" -> {
                val sortedProducts = state.products.sortedBy { it.price }
                state = state.copy(products = sortedProducts)
            }
            "Price High" -> {
                val sortedProducts = state.products.sortedByDescending { it.price }
                state = state.copy(products = sortedProducts)
            }
            else -> {
            }
        }
    }

    private fun filterCategory(category: String) {
        when (category) {
            "All" -> {
                state = state.copy(
                    products = copyProducts
                )
            }
            "Electronics" -> {
                state = state.copy(
                    products = copyProducts
                )
                val filteredElectronics = state.products.filter {
                    it.category == "electronics"
                }
                state = state.copy(
                    products = filteredElectronics
                )
            }
            "Jewelry" -> {
                state = state.copy(
                    products = copyProducts
                )
                val filteredClothes = state.products.filter {
                    it.category == "jewelery"
                }
                state = state.copy(
                    products = filteredClothes
                )
            }
            else -> {}
        }
    }
}