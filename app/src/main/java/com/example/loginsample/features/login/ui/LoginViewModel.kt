package com.example.loginsample.features.login.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loginsample.features.login.data.LoginRepository
import com.example.loginsample.features.login.data.LoginRepositoryImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginRepository: LoginRepository = LoginRepositoryImpl() // In a real world scenario, this would be injected
) : ViewModel() {
    private val _uiState = MutableStateFlow<LoginUiState>(LoginUiState.Idle)
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    var username by mutableStateOf("")
        private set
    var password by mutableStateOf("")
        private set

    fun updateUsername(username: String) {
        _uiState.update { LoginUiState.Idle }
        this.username = username
    }

    fun updatePassword(password: String) {
        _uiState.update { LoginUiState.Idle }
        this.password = password
    }

    fun authenticate() {
        if (username.isEmpty() || password.isEmpty()) {
            _uiState.update { LoginUiState.Error(ErrorType.EmptyCredentials) }
            return
        }
        _uiState.update {
            LoginUiState.Loading
        }
        viewModelScope.launch {
            val authenticated = loginRepository.authenticate(username, password)
            if (authenticated) {
                _uiState.update {
                    LoginUiState.Success(username)
                }
            } else {
                _uiState.update {
                    LoginUiState.Error(ErrorType.InvalidCredentials)
                }
            }
        }
    }
}