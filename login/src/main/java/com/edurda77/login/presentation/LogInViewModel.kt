package com.edurda77.login.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edurda77.domain.repository.ShopRepository
import com.edurda77.domain.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class LogInViewModel @Inject constructor(private val repo: ShopRepository) : ViewModel() {
    private val _state = MutableLiveData<LoginFragmentState>(LoginFragmentState.Empty)
    val state = _state

    fun logInTo(userName: String, password: String) {
        _state.value = LoginFragmentState.Loading
        viewModelScope.launch {
            val result = repo.getLogin(
                user = userName,
                password = password
            )
            when (result) {
                is Resource.Error -> {
                    _state.value =
                        LoginFragmentState.Error(error = result.message ?: "unknown error")
                }
                is Resource.Success -> {
                    _state.value = result.data?.let { LoginFragmentState.Success(data = it, user = userName) }
                }
            }
        }
    }
}