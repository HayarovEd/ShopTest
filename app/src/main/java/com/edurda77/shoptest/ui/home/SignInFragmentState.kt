package com.edurda77.shoptest.ui.home

sealed class SignInFragmentState {
    object Empty: SignInFragmentState()
    object Loading: SignInFragmentState()
    class Error(val error:String): SignInFragmentState()
    class Success (val data:String): SignInFragmentState()
}
