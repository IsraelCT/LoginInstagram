package com.example.logininstagram

import android.annotation.SuppressLint
import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column


import androidx.compose.foundation.layout.Spacer


import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.logininstagram.ui.theme.LoginInstagramTheme

@Composable
fun LoginScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(25.dp)
    ) {

        Header(Modifier.align(Alignment.TopEnd))
        Body(modifier = Modifier.align(Alignment.Center))
    }

}

@Composable
fun Body(modifier: Modifier) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    Column(modifier = modifier) {
        IconLogin(Modifier.align(Alignment.CenterHorizontally))
        Spacer(Modifier.height(25.dp))
        Email(email) { email = it }
        Spacer(Modifier.height(20.dp))
        Password(password) { password = it }
        Spacer(Modifier.height(5.dp))
        ForgetPassword(Modifier.align(Alignment.End))

    }
}

@Composable
fun ForgetPassword(modifier: Modifier) {

    Text(
        "¿ Olvidaste tu contraseña",
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold,
        color = Color.Blue, modifier = modifier
    )
}


@Composable
fun Password(password: String, onPasswordChanged: (String) -> Unit) {
    var passwordHidden by remember { mutableStateOf(true) }
    TextField(
        value = password,
        onValueChange = { onPasswordChanged(it) },
        label = { Text(text = "Ingresa la contraseña ") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        singleLine = true,
        visualTransformation = if (passwordHidden) PasswordVisualTransformation() else VisualTransformation.None,
        trailingIcon = {
            Icon(
                if (passwordHidden) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                contentDescription = null,
                modifier = Modifier.clickable { passwordHidden = !passwordHidden }
            )

        }
    )
}

@Composable
fun Email(email: String, onTextChanged: (String) -> Unit) {
    TextField(
        value = email,
        onValueChange = { onTextChanged(it) },
        label = { Text(text = "Ingresa el Email ") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
    )
}

@Composable
fun IconLogin(modifier: Modifier) {
    Image(
        painter = painterResource(R.drawable.insta),
        contentDescription = null,
        modifier = modifier
    )
}


@SuppressLint("ContextCastToActivity")
@Composable
fun Header(modifier: Modifier) {
    var activity = LocalContext.current as Activity
    Icon(
        imageVector = Icons.Default.Close,
        contentDescription = null, modifier = modifier.clickable {
            activity.finish()
        }
    )
}


@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginInstagramTheme {
        LoginScreen()
    }
}