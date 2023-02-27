package com.edurda77.domain.navigation

sealed interface Action {
    object SignInToLogIn : Action
    object SignInToHome : Action
    object LogInToHome : Action
    object HomeToProduct : Action
    object ProfileToSignIn : Action
}