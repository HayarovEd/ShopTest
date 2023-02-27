package com.edurda77.sigin.presentation

sealed class SignInFragmentState {
    object Empty: SignInFragmentState()
    object Loading: SignInFragmentState()
    class Error(val error:String): SignInFragmentState()
    class Success (val data:String): SignInFragmentState()
}
