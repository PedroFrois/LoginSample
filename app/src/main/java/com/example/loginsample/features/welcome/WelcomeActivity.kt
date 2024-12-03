package com.example.loginsample.features.welcome

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.loginsample.ui.theme.LoginSampleTheme

class WelcomeActivity : ComponentActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        val username = intent.getStringExtra("username")
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            LoginSampleTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    WelcomeScreen(username)
                }
            }
        }
    }
}