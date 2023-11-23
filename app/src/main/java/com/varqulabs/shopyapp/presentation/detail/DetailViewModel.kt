package com.varqulabs.shopyapp.presentation.detail

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.varqulabs.shopyapp.domain.detail.DetailUseCases
import com.varqulabs.shopyapp.utils.Constants.DETAIL_SCREEN_ARGUMENT_KEY
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val detailUseCases: DetailUseCases
) : ViewModel() {

    private val _state = MutableStateFlow(DetailState())
    val state: StateFlow<DetailState> = _state

    init{
        getProductIdArgument()
        getProductDetail()
    }

    private fun getProductIdArgument(){
        _state.value = _state.value.copy(
            selectedProductId = savedStateHandle.get<String>(key = DETAIL_SCREEN_ARGUMENT_KEY)
        )
    }

    fun getProductDetail(){
        if(_state.value.selectedProductId != null){
            viewModelScope.launch {
                _state.value = state.value.copy(isLoading = true)

                try {
                    withContext(Dispatchers.IO){
                        detailUseCases.getProductDetailUseCase(id = _state.value.selectedProductId!!)
                            .collectLatest {
                                _state.value = _state.value.copy(
                                    product = it,
                                    isLoading = false
                                )
                            }
                    }
                } catch (e: Exception) {
                    Log.e("DetailViewModel", "Error obteniendo detalles del producto", e)
                    _state.value = _state.value.copy(
                        isLoading = false,
                        errorMsg = "Error obteniendo detalles del producto"
                    )
                }
            }
        }
    }

}