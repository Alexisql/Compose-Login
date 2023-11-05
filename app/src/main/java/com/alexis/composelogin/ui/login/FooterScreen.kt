package com.alexis.composelogin.ui.login

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.alexis.composelogin.R

@Composable
fun FooterScreen(modifier: Modifier) {
    Column(modifier = modifier) {
        ButtonNewAccount()
        ShowSpacer(size = 15)
        LogoMeta()
    }
}

@Composable
fun ButtonNewAccount() {
    Button(
        onClick = { },
        shape = CircleShape,
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.White
        ),
        border = BorderStroke(1.dp, Color(0x800464E4))
    ) {
        Text(text = stringResource(id = R.string.createNewAccount), color = Color(0xFF046DFA))
    }
}

@Composable
fun LogoMeta() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_meta),
            contentDescription = stringResource(id = R.string.contentDescriptionLogoMeta),
            colorFilter = ColorFilter.tint(Color.Gray)
        )
        Text(
            text = stringResource(id = R.string.footerMeta),
            color = Color.Gray,
            fontWeight = FontWeight.Medium
        )
    }
}