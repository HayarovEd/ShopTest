package com.edurda77.product.presentation

import androidx.lifecycle.*
import com.edurda77.domain.model.ProductDetail
import com.edurda77.domain.usecases.GetProductDetails
import com.edurda77.domain.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(private val getProductDetails: GetProductDetails) :
    ViewModel() {
    private var products = MutableLiveData<ProductDetail?>()
    private val _state = MutableLiveData<ProductFragmentState>(ProductFragmentState.Loading)
    val state = _state

    init {
        getDetails()
    }
    fun getPosition (position: Int) {
        products.value?.let {
            _state.value = ProductFragmentState.Success(
                data = it,
                position = position
            )
        }
    }
    private fun getDetails() {
        viewModelScope.launch{
            when (val result = getProductDetails.invoke()) {
                is Resource.Success -> {
                    _state.value =
                        result.data?.let {
                            ProductFragmentState.Success(
                                data = it
                            )
                        }
                    products.value = result.data
                }
                is Resource.Error -> {
                    _state.value =
                        ProductFragmentState.Error(result.message ?: "An unknown error")
                }
            }
        }
    }
}