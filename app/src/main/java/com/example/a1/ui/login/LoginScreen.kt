package com.example.a1.ui.login

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.a1.R
import com.example.a1.data.profiledata.LoginState
import com.example.a1.data.profiledata.ProfileViewModel
import com.example.a1.ui.components.HeaderText
import com.example.a1.ui.components.LoginTextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import kotlinx.coroutines.delay

val defaultPadding = 16.dp
val itemSpacing = 8.dp

@Composable
fun LoginScreen(viewModel : ProfileViewModel, onLoginClick: () -> Unit, onSignUpClick: () -> Unit) {

    //toast handler
    var isToastVisible by remember { mutableStateOf(false) }
    if (isToastVisible) {
        LaunchedEffect(Unit) {
            delay(2000) // Adjust the delay as needed
            isToastVisible = false
        }
    }

    val (userName, setUsername) = rememberSaveable {
    mutableStateOf("")
}
    val (password, setPassword) = rememberSaveable {
    mutableStateOf("")
}
    val (checked, onCheckedChange) = rememberSaveable {
    mutableStateOf(false)
}
    val isFieldsEmpty = userName.isNotEmpty() && password.isNotEmpty()
    val context = LocalContext.current

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
                if (!isToastVisible) Toast.makeText(context, "Login Successful.", Toast.LENGTH_SHORT).show()
                isToastVisible = true
                onLoginClick()
                viewModel.resetLoginState()
            }
            is LoginState.Failure -> {
                if (!isToastVisible) Toast.makeText(context, "Login Failed. No Account.", Toast.LENGTH_SHORT).show()
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

        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(defaultPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HeaderText(
                text = "Login",
                modifier = Modifier
                    .padding(vertical = defaultPadding)
                    .align(alignment = Alignment.Start)
            )
            LoginTextField(
                value = userName ,
                onValueChange = setUsername,
                labelText = "Username",
                leadingIcon = Icons.Default.Person,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(itemSpacing))
            LoginTextField(
                value = password ,
                onValueChange = setPassword,
                labelText = "Password",
                leadingIcon = Icons.Default.Lock,
                modifier = Modifier.fillMaxWidth(),
                keyboardType = KeyboardType.Password,
                visualTransformation = PasswordVisualTransformation()
            )
            Spacer(Modifier.height(itemSpacing))
            Button(
                onClick = {
                    viewModel.login(email = userName, password = password)
                },
                modifier = Modifier.fillMaxWidth(),
                enabled = isFieldsEmpty
            ) {
                Text("Login")
            }
            AlternativeLoginOptions(
                onIconClick = {index ->
                    when(index){
                        0 -> {
                            Toast.makeText(context, "Instagram Login Click", Toast.LENGTH_SHORT).show()
                        }
                        1 -> {
                            Toast.makeText(context, "GitHub Login Click", Toast.LENGTH_SHORT).show()
                        }
                        2 -> {
                            Toast.makeText(context, "Google Login Click", Toast.LENGTH_SHORT).show()
                        }
                    }
                },
                onSignUpClick = onSignUpClick,
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(align = Alignment.BottomCenter)
            )
        }
    }


}

@Composable
fun AlternativeLoginOptions(
    onIconClick:(index: Int) -> Unit,
    onSignUpClick:() -> Unit,
    modifier: Modifier = Modifier
    ){
    Spacer(Modifier.height(itemSpacing))
    Row (
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ){
        Text("Don't have an Account?")
        Spacer(Modifier.height(itemSpacing))
        TextButton(onClick = onSignUpClick) {
            Text("Sign Up")
        }
    }

}

