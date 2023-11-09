package com.alexis.composelogin.domain

import androidx.compose.ui.graphics.Color

data class Login(
    var email: String = "",
    var password: String = "",
    var enableBottom: Boolean = false,
    var borderColorEmail: Color = Color(0xFFCCCCCC),
    var borderColorPassword: Color = Color(0xFFCCCCCC)
)
