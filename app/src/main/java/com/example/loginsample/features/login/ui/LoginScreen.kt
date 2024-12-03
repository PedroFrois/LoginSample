package com.example.loginsample.features.login.ui

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun LoginScreen(
    loginViewModel: LoginViewModel,
    onSuccessfulLogin: (username: String) -> Unit
) {
    val context = LocalContext.current.applicationContext
    val uiState = loginViewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = loginViewModel.username,
            onValueChange = { loginViewModel.updateUsername(it) },
            label = { Text("Username") },
            isError = uiState.value.isError(),
            enabled = uiState.value.isLoading().not(),
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = loginViewModel.password,
            onValueChange = { loginViewModel.updatePassword(it) },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            isError = uiState.value.isError(),
            enabled = uiState.value.isLoading().not(),
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            enabled = uiState.value.isLoading().not(),
            onClick = {
                loginViewModel.authenticate()
            },
        ) {
            Text("Login")
        }

        if (uiState.value.isLoading())
            CircularProgressIndicator()

        if (uiState.value.isSuccess())
            onSuccessfulLogin(loginViewModel.username)

        if (uiState.value.isError())
            uiState.value.let {
                it as LoginUiState.Error
                when (it.errorType) {
                    ErrorType.InvalidCredentials -> Toast.makeText(
                        context,
                        "Invalid username or password",
                        Toast.LENGTH_SHORT
                    ).show()

                    ErrorType.EmptyCredentials -> Toast.makeText(
                        context,
                        "Please enter username and password",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }
}