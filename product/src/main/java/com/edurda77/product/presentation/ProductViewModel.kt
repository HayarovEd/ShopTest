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
    //private var amount = MutableLiveData<ProductDetail?>()
    private val _state = MutableLiveData<ProductFragmentState>(ProductFragmentState.Loading)
    val state = _state
    var price = 0
    private val _amount = MutableLiveData(0)
    val amount = _amount
    private val _count = MutableLiveData(0)
    val count = _count

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

    fun changeCount(changeCount:Int) {
        viewModelScope.launch {
            if (_count.value==0&&changeCount==-1){
                return@launch
            } else {
                _count.value = _count.value?.plus(changeCount)
                _amount.value = count.value?.times(price)
            }
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
                    price = result.data?.price ?: 0
                }
                is Resource.Error -> {
                    _state.value =
                        ProductFragmentState.Error(result.message ?: "An unknown error")
                }
            }
        }
    }
}