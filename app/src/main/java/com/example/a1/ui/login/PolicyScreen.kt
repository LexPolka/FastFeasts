package com.example.a1.ui.login

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun PolicyScreen(onBtnClick:() -> Unit){
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
        Column {
            Text("we have no policy LOLLLL HAHAH")
            Button(onClick = onBtnClick) {
                Text("Finish")
            }

        }
    }
}
