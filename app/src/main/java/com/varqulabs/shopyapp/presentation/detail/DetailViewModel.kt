package com.varqulabs.shopyapp.presentation.detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.varqulabs.shopyapp.domain.detail.DetailUseCases
import com.varqulabs.shopyapp.domain.model.Product
import com.varqulabs.shopyapp.utils.Constants.DETAIL_SCREEN_ARGUMENT_KEY
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val detailUseCases: DetailUseCases
) : ViewModel() {

    var state by mutableStateOf(DetailState())
        private set

    init{
        getProductIdArgument()
        getProductDetails()
    }

    private fun getProductIdArgument(){
        state = state.copy(
            selectedProductId = savedStateHandle.get<String>(key = DETAIL_SCREEN_ARGUMENT_KEY)
        )
    }


    private fun getProductDetails(){
        if(state.selectedProductId != null){
            viewModelScope.launch {
                state = state.copy(isLoading = false)
                detailUseCases.getProductDetailUseCase(id = state.selectedProductId!!.toInt())
                    .collectLatest {
                        it.onSuccess { product ->
                            state = state.copy(
                                product = product
                            )
                        }.onFailure { error ->
                            state = state.copy(
                                errorMsg = error.message
                            )
                        }
                    }

            }
        }
    }

}