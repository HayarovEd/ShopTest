package com.edurda77.product.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edurda77.domain.usecases.GetProductDetails
import com.edurda77.domain.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(private val getProductDetails: GetProductDetails) :
    ViewModel() {
    private val _state = MutableLiveData<ProductFragmentState>(ProductFragmentState.Loading)
    val state = _state

    init {
        getDetails()
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
                }
                is Resource.Error -> {
                    _state.value =
                        ProductFragmentState.Error(result.message ?: "An unknown error")
                }
            }
        }
    }
}