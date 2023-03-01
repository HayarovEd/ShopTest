package com.edurda77.home.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.edurda77.domain.model.Category
import com.edurda77.domain.utils.categoriesList
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {
    private val _categories = MutableLiveData<List<Category>>()
    val categories = _categories

    init {
        getCategories()
    }

    private fun getCategories() {
        _categories.value = categoriesList
    }
}