package com.example.loginsample

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.loginsample.features.login.ui.LoginScreen
import com.example.loginsample.features.welcome.WelcomeActivity
import com.example.loginsample.ui.theme.LoginSampleTheme
import com.example.loginsample.features.login.ui.LoginViewModel

class MainActivity : ComponentActivity() {
    private val loginViewModel =
        LoginViewModel() // In a real world scenario, this would be injected

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            LoginSampleTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    LoginScreen(loginViewModel,
                        onSuccessfulLogin = { username ->
                            val intent = Intent(this, WelcomeActivity::class.java)
                            intent.putExtra("username", username)
                            startActivity(intent)
                        })
                }
            }
        }
    }
}


