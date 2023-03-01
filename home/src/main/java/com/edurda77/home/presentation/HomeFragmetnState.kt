package com.edurda77.home.presentation

sealed class HomeFragmetnState {
    object Loading: HomeFragmetnState()
    class Error(val error:String): HomeFragmetnState()
    class Success (val data:String, val user:String): HomeFragmetnState()
}
