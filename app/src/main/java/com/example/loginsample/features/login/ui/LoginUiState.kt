package com.example.loginsample.features.login.ui

sealed class LoginUiState{
    data class Success(val username: String) : LoginUiState()
    data class Error(val errorType: ErrorType) : LoginUiState()
    data object Loading : LoginUiState()
    data object Idle : LoginUiState()

    fun isLoading() = this is Loading
    fun isSuccess() = this is Success
    fun isError() = this is Error
}

