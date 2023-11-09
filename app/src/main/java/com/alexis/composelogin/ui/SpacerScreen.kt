package com.alexis.composelogin.ui

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ShowSpacer(size: Int) {
    Spacer(modifier = Modifier.size(size.dp))
}