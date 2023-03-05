package com.edurda77.home.presentation

import com.edurda77.domain.model.ElementSearch

sealed class SearcheState {
    object Empty: SearcheState()
    object Loading: SearcheState()
    class Error(val error:String): SearcheState()
    class Success (val resultSearch: List<String>, val isShowResult: Boolean): SearcheState()
}
