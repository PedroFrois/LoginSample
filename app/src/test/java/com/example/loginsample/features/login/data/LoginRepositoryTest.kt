package com.example.loginsample.features.login.data

import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class LoginRepositoryTest {
    private val sut: LoginRepository = LoginRepositoryImpl()

    @Test
    fun authenticate_validCredentials_returnsTrue() = runTest {
        val response = sut.authenticate("admin", "password")
        assertEquals(true, response)
    }

    @Test
    fun authenticate_invalidCredentials_returnsFalse() = runTest {
        val response = sut.authenticate("wrong", "credentials")
        assertEquals(false, response)
    }
}