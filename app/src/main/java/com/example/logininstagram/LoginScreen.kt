@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.logininstagram

import android.annotation.SuppressLint
import android.app.Activity
import android.provider.CalendarContract.Colors
import android.util.Patterns
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row


import androidx.compose.foundation.layout.Spacer


import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.traceEventEnd
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
        Footer(Modifier.align(Alignment.BottomCenter))
    }

}

@Composable
fun Footer(modifier: Modifier) {
    Column(modifier = modifier.fillMaxWidth()) {

        HorizontalDivider(thickness = 1.dp)
        Spacer(Modifier.size(24.dp))
        SignUp()
        Spacer(Modifier.size(24.dp))


    }

}

@Composable
fun SignUp() {
    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
        Text(text = "Don´t have an account?")
        Spacer(Modifier.height(16.dp))
        Text(
            text = "Sign up",
            Modifier.padding(horizontal = 6.dp),
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF4EABE9)
        )
    }
}

@Composable
fun Body(modifier: Modifier) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isLoginEnable by remember { mutableStateOf(false) }
    Column(modifier = modifier) {
        IconLogin(Modifier.align(Alignment.CenterHorizontally))
        Spacer(Modifier.height(25.dp))
        Email(email) {
            email = it
            isLoginEnable = enableLogin(email, password)
        }
        Spacer(Modifier.height(20.dp))
        Password(password) {
            password = it
            isLoginEnable = enableLogin(email, password)
        }
        Spacer(Modifier.height(5.dp))
        ForgetPassword(Modifier.align(Alignment.End))
        Spacer(Modifier.height(16.dp))
        LoginButton(isLoginEnable)
        Spacer(Modifier.height(16.dp))
        DividerLogin()
        Spacer(modifier = Modifier.size(32.dp))
        SocialLogin()


    }
}

@Composable
fun SocialLogin() {
    Row(
        Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(R.drawable.fb),
            contentDescription = "Social login",
            modifier = Modifier.size(16.dp)
        )
        Text(
            text = "Continua como Israel",
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(6.dp), color = Color(0xFF4EA8E9)
        )

    }
}

@Composable
fun DividerLogin() {
    Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        HorizontalDivider(
            thickness = 1.dp, modifier = Modifier
                .background(Color.Black)
                .weight(1f)
        )
        Text(
            "Or",
            modifier = Modifier.padding(horizontal = 6.dp),
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold
        )
        HorizontalDivider(
            thickness = 1.dp, modifier = Modifier
                .weight(1f)
                .background(Color.Black)
        )
    }
}


@Composable
fun LoginButton(loginEnable: Boolean) {
    Button(
        onClick = { },
        enabled = loginEnable,
        modifier = Modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF4EA8E9),
            disabledContainerColor = Color(0xFF78c8f9),
            contentColor = Color.White,
            disabledContentColor = Color.White


        )
    ) {
        Text("Login ")

    }
}

fun enableLogin(email: String, password: String): Boolean {
    return Patterns.EMAIL_ADDRESS.matcher(email).matches() &&
            password.length > 6
}

@Composable
fun ForgetPassword(modifier: Modifier) {

    Text(
        "¿ Olvidaste tu contraseña",
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold,
        color = Color(0xFF4EA8E9),
        modifier = modifier
    )
}


@ExperimentalMaterial3Api
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Password(password: String, onPasswordChanged: (String) -> Unit) {
    var passwordHidden by remember { mutableStateOf(true) }
    TextField(
        value = password,
        onValueChange = { onPasswordChanged(it) },
        label = { Text(text = "Ingresa la contraseña ") }, placeholder = { Text("assddffg") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),

        singleLine = true,
        maxLines = 1,
        visualTransformation = if (passwordHidden) PasswordVisualTransformation() else VisualTransformation.None,
        trailingIcon = {
            Icon(
                if (passwordHidden) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                contentDescription = null,
                modifier = Modifier.clickable { passwordHidden = !passwordHidden }
            )

        }, modifier = Modifier.fillMaxWidth(),
        colors = TextFieldDefaults.textFieldColors(
            focusedTextColor = Color(0xFFB2B2B2),
            containerColor = Color(0xFFFAFAFA),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent


        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Email(email: String, onTextChanged: (String) -> Unit) {
    TextField(
        value = email,
        onValueChange = { onTextChanged(it) }, singleLine = true, maxLines = 1,
        label = { Text(text = "Ingresa el Email ") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        modifier = Modifier
            .fillMaxWidth(),
        colors = TextFieldDefaults.textFieldColors(
            focusedTextColor = Color(0xFFB2B2B2),
            containerColor = Color(0xFFFAFAFA),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent


        )
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