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
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldColors
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
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
import androidx.compose.ui.text.style.TextOverflow
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
    var visibilityIcon by rememberSaveable { mutableStateOf(false) }
    TextFieldLogin(
        text = email,
        borderColor = borderColor,
        label = stringResource(id = R.string.labelTextFieldEmail),
        keyboardType = KeyboardType.Email,
        onTextChanged = onTextChanged,
        onBorderColorChanged = onBorderColorChanged,
        trailingIcon = {
            if (visibilityIcon && email.isNotEmpty()) {
                IconDeleteEmail { onTextChanged(it) }
            }
        },
        onVisibilityIconChanged = { visibilityIcon = it }
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
    var visibilityIcon by rememberSaveable { mutableStateOf(false) }

    TextFieldLogin(
        text = password,
        label = stringResource(id = R.string.labelTextFieldPassword),
        borderColor = borderColor,
        keyboardType = KeyboardType.Password,
        visualTransformation = if (passwordHidden) PasswordVisualTransformation() else VisualTransformation.None,
        onTextChanged = onTextChanged,
        onBorderColorChanged = onBorderColorChanged,
        trailingIcon = {
            if (visibilityIcon || password.isNotEmpty()) {
                IconPassword(passwordHidden) { passwordHidden = it }
            }
        },
        onVisibilityIconChanged = { visibilityIcon = it }
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
    trailingIcon: @Composable (() -> Unit)? = null,
    onVisibilityIconChanged: (Boolean) -> Unit
) {
    TextField(
        value = text,
        onValueChange = { onTextChanged(it) },
        modifier = Modifier
            .fillMaxWidth()
            .onFocusChanged {
                val isFocused = it.isFocused
                onVisibilityIconChanged(isFocused)
                if (isFocused) {
                    onBorderColorChanged(0xFF444444)
                } else {
                    onBorderColorChanged(0xFFCCCCCC)
                }
            }
            .border(1.dp, Color(borderColor), RoundedCornerShape(15.dp)),
        label = {
            Text(
                text = label,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        maxLines = 1,
        singleLine = true,
        shape = RoundedCornerShape(15.dp),
        visualTransformation = visualTransformation,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        trailingIcon = trailingIcon,
        colors = TextFieldColorsLogin()
    )
}

@Composable
fun IconPassword(passwordHidden: Boolean, onVisibilityChanged: (Boolean) -> Unit) {
    val iconVisibility = if (passwordHidden) {
        Icons.Outlined.VisibilityOff
    } else {
        Icons.Outlined.Visibility
    }
    IconButton(onClick = { onVisibilityChanged(!passwordHidden) }) {
        Icon(
            imageVector = iconVisibility,
            contentDescription = stringResource(id = R.string.contentDescriptionIconPassword),
            tint = Color.DarkGray
        )
    }
}

@Composable
fun IconDeleteEmail(onDeleteEmail: (String) -> Unit) {
    IconButton(onClick = { onDeleteEmail("") }) {
        Icon(
            imageVector = Icons.Outlined.Clear,
            contentDescription = stringResource(id = R.string.contentDescriptionIconEmail),
            tint = Color.DarkGray
        )
    }
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
