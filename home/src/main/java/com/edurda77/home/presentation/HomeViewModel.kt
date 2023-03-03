package com.edurda77.home.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edurda77.domain.model.Category
import com.edurda77.domain.model.ElementFlashSale
import com.edurda77.domain.model.ElementLatest
import com.edurda77.domain.usecases.GetFlashSaleProducts
import com.edurda77.domain.usecases.GetLatestProducts
import com.edurda77.domain.usecases.GetSearchedProducts
import com.edurda77.domain.utils.Resource
import com.edurda77.domain.utils.categoriesList
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getSearchedProducts: GetSearchedProducts,
    private val getLatestProducts: GetLatestProducts,
    private val getFlashSaleProducts: GetFlashSaleProducts
) : ViewModel() {

    private val _state = MutableLiveData<HomeFragmetnState>(HomeFragmetnState.Loading)
    val state = _state

    private val _stateSearch = MutableLiveData<SearcheState>(SearcheState.Empty)
    val stateSearch = _stateSearch

    private val _categories = MutableLiveData<List<Category>>()
    val categories = _categories
    init {
        getCategories()
        getData ()
    }

    private fun getCategories() {
        _categories.value = categoriesList
    }


    fun searchByChars(query: String) {
        _stateSearch.value = SearcheState.Loading
        viewModelScope.launch {
            delay(1000)
            when (val result = getSearchedProducts.invoke(query)) {
                is Resource.Error -> {
                    _stateSearch.value =
                        SearcheState.Error(result.message ?: "An unknown error")
                }
                is Resource.Success -> {
                    if (result.data==null ||query.isBlank()|| result.data!!.isEmpty()) {
                        _stateSearch.value =
                            SearcheState.Success(
                                resultSearch =  emptyList(),
                                isShowResult = false
                            )
                    } else {
                        _stateSearch.value =
                            SearcheState.Success(
                                resultSearch = result.data!!,
                                isShowResult = true
                            )
                    }
                }
            }
        }
    }
    private fun getData () {
        viewModelScope.launch{
            val latest = async { getLatestProducts.invoke() }
            val flashSales = async { getFlashSaleProducts.invoke() }
            when (val resultLatest = latest.await()) {
                is Resource.Success -> {
                    when (val resultFlashSales = flashSales.await()) {
                        is Resource.Success -> {
                            _state.value =
                                HomeFragmetnState.Success(
                                    latest = resultLatest.data?: emptyList(),
                                    flashSales = resultFlashSales.data?: emptyList()
                                )
                        }
                        is Resource.Error -> {
                            _state.value =
                                HomeFragmetnState.Error(resultFlashSales.message ?: "An unknown error")
                        }
                    }
                }
                is Resource.Error -> {
                    _state.value =
                        HomeFragmetnState.Error(resultLatest.message ?: "An unknown error")
                }
            }
        }
    }
}