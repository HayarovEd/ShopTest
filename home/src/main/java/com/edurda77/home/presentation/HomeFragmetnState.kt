package com.edurda77.home.presentation

import com.edurda77.domain.model.ElementFlashSale
import com.edurda77.domain.model.ElementLatest

sealed class HomeFragmetnState {
    object Loading: HomeFragmetnState()
    class Error(val error:String): HomeFragmetnState()
    class Success (
        val latest:List<ElementLatest>,
        val flashSales: List<ElementFlashSale>
    ): HomeFragmetnState()
}
