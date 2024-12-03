package com.example.loginsample.features.login.data

import kotlinx.coroutines.time.delay
import java.time.Duration

interface LoginRepository {
    suspend fun authenticate(username: String, password: String): Boolean
}


class LoginRepositoryImpl : LoginRepository {
    override suspend fun authenticate(username: String, password: String): Boolean {
        delay(Duration.ofSeconds(1)) // simulating backend call
        return username == "admin" && password == "password" // in a real world scenario, this would be a backend call
    }
}