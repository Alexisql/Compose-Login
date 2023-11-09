package com.alexis.composelogin.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.alexis.composelogin.ui.theme.ComposeLoginTheme
import com.alexis.composelogin.ui.viewmodel.LoginViewModel

class MainActivity : ComponentActivity() {
    private val loginViewModel by viewModels<LoginViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeLoginTheme {
                val brush = Brush.horizontalGradient(
                    listOf(
                        Color(0x8F10202),
                        Color(0x8FFE222),
                        Color(0x80019FF)
                    )
                )
                LoginScreen(Modifier.background(brush = brush), loginViewModel)
            }
        }
    }
}

@Composable
fun LoginScreen(modifier: Modifier, loginViewModel: LoginViewModel) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        HeaderScreen(Modifier.align(Alignment.TopStart))
        BodyScreen(Modifier.align(Alignment.Center), loginViewModel)
        FooterScreen(Modifier.align(Alignment.BottomCenter))
    }
}