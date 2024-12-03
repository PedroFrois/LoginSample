package com.example.loginsample.features.welcome

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.loginsample.R

@Composable
fun WelcomeScreen(
    username: String?
) {
    val context = LocalContext.current.applicationContext
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        username?.let {
            Text(context.getString(R.string.welcome_title_with_username, it))
        } ?: run {
            Text(context.getString(R.string.welcome_title))
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(context.getString(R.string.welcome_message))
    }
}