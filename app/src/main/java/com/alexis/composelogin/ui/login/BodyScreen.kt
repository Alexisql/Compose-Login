package com.alexis.composelogin.ui.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldColors
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.alexis.composelogin.R

@Composable
fun BodyScreen(modifier: Modifier) {
    val borderColorTexFieldDefault = 0xFFCCCCCC
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var borderColorEmail by rememberSaveable { mutableStateOf(borderColorTexFieldDefault) }
    var borderColorPassword by rememberSaveable { mutableStateOf(borderColorTexFieldDefault) }

    Column(modifier = modifier) {
        Image(
            painter = painterResource(id = R.drawable.logo_instagram),
            contentDescription = stringResource(id = R.string.contentDescriptionLogoInstagram),
            modifier = Modifier
                .size(60.dp)
                .align(Alignment.CenterHorizontally)
        )
        ShowSpacer(size = 100)
        TextFieldEmail(
            email = email,
            borderColor = borderColorEmail,
            onTextChanged = { email = it },
            onBorderColorChanged = { borderColorEmail = it })
        ShowSpacer(size = 10)
        TextFieldPassword(
            password = password,
            borderColor = borderColorPassword,
            onTextChanged = { password = it }
        ) {
            borderColorPassword = it
        }
        ShowSpacer(size = 12)
        ButtonLogin(enabled = true)
        ShowSpacer(size = 14)
        ForgotPassword(Modifier.align(Alignment.CenterHorizontally))
    }
}

@Composable
fun TextFieldEmail(
    email: String,
    borderColor: Long,
    onTextChanged: (String) -> Unit,
    onBorderColorChanged: (Long) -> Unit
) {
    TextFieldLogin(
        text = email,
        borderColor = borderColor,
        label = stringResource(id = R.string.labelTextFieldEmail),
        keyboardType = KeyboardType.Email,
        onTextChanged = onTextChanged,
        onBorderColorChanged = onBorderColorChanged
    )
}

@Composable
fun TextFieldPassword(
    password: String,
    borderColor: Long,
    onTextChanged: (String) -> Unit,
    onBorderColorChanged: (Long) -> Unit
) {
    var passwordHidden by rememberSaveable { mutableStateOf(true) }
    TextFieldLogin(
        text = password,
        label = stringResource(id = R.string.labelTextFieldPassword),
        borderColor = borderColor,
        keyboardType = KeyboardType.Password,
        visualTransformation = if (passwordHidden) PasswordVisualTransformation() else VisualTransformation.None,
        onTextChanged = onTextChanged,
        onBorderColorChanged = onBorderColorChanged
    )
}

@Composable
fun ButtonLogin(enabled: Boolean) {
    Button(
        onClick = { },
        enabled = enabled,
        shape = CircleShape,
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
    ) {
        Text(text = stringResource(id = R.string.textButtonLogIn))
    }
}

@Composable
fun ForgotPassword(modifier: Modifier) {
    Text(
        text = stringResource(id = R.string.textForgotPassword),
        fontWeight = FontWeight.Medium,
        modifier = modifier
    )
}

@Composable
fun TextFieldLogin(
    text: String,
    label: String,
    borderColor: Long,
    keyboardType: KeyboardType,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    onTextChanged: (String) -> Unit,
    onBorderColorChanged: (Long) -> Unit,
) {
    TextField(
        value = text,
        onValueChange = { onTextChanged(it) },
        modifier = Modifier
            .fillMaxWidth()
            .onFocusChanged {
                if (it.isFocused) {
                    onBorderColorChanged(0xFF444444)
                } else {
                    onBorderColorChanged(0xFFCCCCCC)
                }
            }
            .border(1.dp, Color(borderColor), RoundedCornerShape(15.dp)),
        label = { Text(text = label) },
        maxLines = 1,
        singleLine = true,
        visualTransformation = visualTransformation,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        colors = TextFieldColorsLogin()
    )
}

@Composable
fun TextFieldColorsLogin(): TextFieldColors {
    return TextFieldDefaults.textFieldColors(
        backgroundColor = Color.White,
        cursorColor = Color.DarkGray,
        focusedIndicatorColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent,
        focusedLabelColor = Color.DarkGray,
        unfocusedLabelColor = Color.LightGray
    )
}
