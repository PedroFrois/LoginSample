package com.example.loginsample.features.login.ui

import app.cash.turbine.test
import com.example.loginsample.commons.MainCoroutineRule
import com.example.loginsample.features.login.data.LoginRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock

@OptIn(ExperimentalCoroutinesApi::class)
class LoginViewModelTest {

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()
    private lateinit var loginRepository: LoginRepository
    private lateinit var viewModel: LoginViewModel

    @Before
    fun setup() {
        loginRepository = mock {
            onBlocking { authenticate("admin", "password") } doReturn true
            onBlocking { authenticate("admin", "wrong_password") } doReturn false
        }
        viewModel = LoginViewModel(loginRepository)
    }

    @Test
    fun `updateUsername updates username state`() = runTest {
        viewModel.updateUsername("testuser")
        assertEquals("testuser", viewModel.username)
    }

    @Test
    fun `updatePassword updates password state`() = runTest {
        viewModel.updatePassword("testpassword")
        assertEquals("testpassword", viewModel.password)
    }

    @Test
    fun `authenticate with empty credentials emits Error state`() = runTest {
        viewModel.uiState.test {
            assertEquals(LoginUiState.Idle, awaitItem())
            viewModel.authenticate()
            assertEquals(LoginUiState.Error(ErrorType.EmptyCredentials), awaitItem())
        }
    }

    @Test
    fun `authenticate with valid credentials emits Success state`() = runTest {
        viewModel.updateUsername("admin")
        viewModel.updatePassword("password")

        viewModel.uiState.test {
            assertEquals(LoginUiState.Idle, awaitItem())
            viewModel.authenticate()
            assertEquals(LoginUiState.Loading, awaitItem())
            assertEquals(LoginUiState.Success("admin"), awaitItem())
        }
    }

    @Test
    fun `authenticate with invalid credentials emits Error state`() = runTest {
        viewModel.updateUsername("admin")
        viewModel.updatePassword("wrong_password")

        viewModel.uiState.test {
            assertEquals(LoginUiState.Idle, awaitItem())
            viewModel.authenticate()
            assertEquals(LoginUiState.Loading, awaitItem())
            assertEquals(LoginUiState.Error(ErrorType.InvalidCredentials), awaitItem())
        }
    }
}