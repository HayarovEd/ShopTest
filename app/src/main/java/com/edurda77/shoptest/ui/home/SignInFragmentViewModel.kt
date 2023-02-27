package com.edurda77.shoptest.ui.home

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

    fun registrNewUser(userName: String, password: String, email: String) {
        _state.value = SignInFragmentState.Loading
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
                        _state.value = SignInFragmentState.Error(error = result.message?: "unknown error")
                }
                is Resource.Success -> {
                        _state.value = result.data?.let { SignInFragmentState.Success(data = it) }
                }
            }
        }

    }
}