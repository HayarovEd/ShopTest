package com.edurda77.login.presentation

sealed class LoginFragmentState {
    object Empty: LoginFragmentState()
    object Loading: LoginFragmentState()
    class Error(val error:String): LoginFragmentState()
    class Success (val data:String, val user:String): LoginFragmentState()
}
