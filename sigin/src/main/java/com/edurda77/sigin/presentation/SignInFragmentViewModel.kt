package com.edurda77.sigin.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edurda77.domain.model.User
import com.edurda77.domain.repository.ShopRepository
import com.edurda77.domain.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class SignInFragmentViewModel @Inject constructor(private val repo: ShopRepository) : ViewModel() {
    private val _state = MutableLiveData<SignInFragmentState>(SignInFragmentState.Empty)
    val state = _state

    fun registerNewUser(userName: String, password: String, email: String) {
        _state.value = SignInFragmentState.Loading
        if (isEmailValid (email)) {
            viewModelScope.launch {
                val result = repo.insertNewUser(
                    User(
                        user = userName,
                        password = password,
                        email = email
                    )
                )
                when (result) {
                    is Resource.Error -> {
                        _state.value =
                            SignInFragmentState.Error(error = result.message ?: "unknown error")
                    }
                    is Resource.Success -> {
                        _state.value = result.data?.let { SignInFragmentState.Success(data = it) }
                    }
                }
            }
        } else {
            _state.value = SignInFragmentState.Error(error = "invalid email")
        }
    }
    private fun isEmailValid(address:String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(address).matches()
    }
}