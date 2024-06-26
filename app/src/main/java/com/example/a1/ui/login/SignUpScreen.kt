package com.example.a1.ui.login

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.example.a1.data.profiledata.LoginState
import com.example.a1.data.profiledata.ProfileViewModel
import com.example.a1.ui.components.HeaderText
import com.example.a1.ui.components.LoginTextField
import kotlinx.coroutines.delay

@Composable
fun SignUpScreen(
    viewModel : ProfileViewModel,
    onSignUpClick:() -> Unit,
    onLoginClick:() -> Unit,
    onPolicyClick:() -> Unit,
    onPrivacyClick:() -> Unit,
) {
    //toast handler
    var isToastVisible by remember { mutableStateOf(false) }
    if (isToastVisible) {
        LaunchedEffect(Unit) {
            delay(2000) // Adjust the delay as needed
            isToastVisible = false
        }
    }

    val (email, onEmailChange) = rememberSaveable { mutableStateOf("") }
    val (password, onPasswordChange) = rememberSaveable { mutableStateOf("") }
    val (confirmPassword, onConfirmPasswordChange) = rememberSaveable { mutableStateOf("") }
    val (agree, onAgreeChange) = rememberSaveable { mutableStateOf(false) }
    val context = LocalContext.current
    var isPasswordSame by remember { mutableStateOf(false) }
    val isFieldsNotEmpty = email.isNotEmpty() && password.isNotEmpty() && confirmPassword.isNotEmpty() && agree

    val startColor = Color(0xFFFF9D7E)
    val endColor = Color(0xFF975743)
    //Screen settings
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    var HeaderBarHeight = (screenHeight*7/100)

    val loginState by viewModel.loginState.collectAsState()

    // Handle login state changes
    LaunchedEffect(loginState) {
        when (loginState) {
            is LoginState.Success -> {
                if (!isToastVisible) Toast.makeText(context, "Register Successful.", Toast.LENGTH_SHORT).show()
                isToastVisible = true
                onSignUpClick()
                viewModel.resetLoginState()
            }
            is LoginState.Failure -> {
                if (!isToastVisible) Toast.makeText(context, "Register Failed. Account Exists.", Toast.LENGTH_SHORT).show()
                isToastVisible = true
                viewModel.resetLoginState()
            }
            else -> Unit
        }
    }

    Column {
        Surface(
            color = Color.Transparent,
            modifier = Modifier
                .height(HeaderBarHeight)
                .fillMaxWidth()
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(startColor, endColor)
                    )
                )
        ) {}

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(defaultPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AnimatedVisibility(isPasswordSame) {
                Text("Password not matched", color = MaterialTheme.colorScheme.onBackground)
            }
            HeaderText(
                text = "Sign Up",
                modifier = Modifier
                    .padding(vertical = defaultPadding)
                    .align(alignment = Alignment.Start)
            )
            LoginTextField(
                value = email,
                onValueChange = onEmailChange,
                labelText = "Email",
                leadingIcon = Icons.Default.Email,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(defaultPadding))
            LoginTextField(
                value = password,
                onValueChange = onPasswordChange,
                labelText = "Password",
                modifier = Modifier.fillMaxWidth(),
                leadingIcon = Icons.Default.Lock,
                keyboardType = KeyboardType.Password
            )
            Spacer(Modifier.height(defaultPadding))
            LoginTextField(
                value = confirmPassword,
                onValueChange = onConfirmPasswordChange,
                labelText = "Confirm Password",
                modifier = Modifier.fillMaxWidth(),
                leadingIcon = Icons.Default.Lock,
                keyboardType = KeyboardType.Password
            )
            Spacer(Modifier.height(defaultPadding))
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                val privacyText = " Privacy Policy "
                val policyText = " Terms & Conditions "
                val annotatedString = buildAnnotatedString {
                    withStyle(SpanStyle(color = MaterialTheme.colorScheme.onBackground)) {
                        append("I agree with")
                    }
                    withStyle(SpanStyle(color = MaterialTheme.colorScheme.primary)) {
                        pushStringAnnotation(tag = privacyText, privacyText)
                        append(privacyText)
                    }
                    withStyle(SpanStyle(color = MaterialTheme.colorScheme.onBackground)) {
                        append("and")
                    }
                    withStyle(SpanStyle(color = MaterialTheme.colorScheme.primary)) {
                        pushStringAnnotation(tag = policyText, policyText)
                        append(policyText)
                    }
                }

                Checkbox(agree, onAgreeChange)
                ClickableText(
                    annotatedString,
                ) { offset ->
                    annotatedString.getStringAnnotations(offset, offset).forEach {
                        when (it.tag) {
                            privacyText -> {
                                Toast.makeText(context, "Privacy Policy Clicked", Toast.LENGTH_SHORT)
                                    .show()
                                onPrivacyClick()
                            }

                            policyText -> {
                                Toast.makeText(context, "Terms & Conditions Clicked", Toast.LENGTH_SHORT)
                                    .show()
                                onPolicyClick()
                            }
                        }
                    }
                }
            }

            Spacer(Modifier.height(defaultPadding + 8.dp))
            Button(onClick = {
                isPasswordSame = password != confirmPassword
                if (!isPasswordSame){
                    viewModel.register(email, password)
                }
            },
                modifier = Modifier.fillMaxWidth(),
                enabled = isFieldsNotEmpty,
            ) {
                Text("Sign Up")
            }
            Spacer(Modifier.height(defaultPadding))
            val signTx = "Sign In"
            val signInAnnotation = buildAnnotatedString {
                withStyle(SpanStyle(color = MaterialTheme.colorScheme.onBackground)) {
                    append ("Already have an account?")
                }
                append("   ")
                withStyle(SpanStyle(color = MaterialTheme.colorScheme.primary)){
                    pushStringAnnotation(signTx,signTx)
                    append(signTx)
                }
            }
            ClickableText(
                signInAnnotation,
            ){offset ->
                signInAnnotation.getStringAnnotations(offset,offset).forEach{
                    if (it.tag == signTx){
                        Toast.makeText(context, "Sign In Clicked", Toast.LENGTH_SHORT).show()
                        onLoginClick()
                    }
                }

            }
        }

    }


}

