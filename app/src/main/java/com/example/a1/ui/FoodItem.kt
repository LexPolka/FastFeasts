package com.example.a1.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController

@Composable
fun InvidiualFoodPage(viewModel: FFViewModel, navController: NavHostController) {
    val foodState by viewModel.foodState.collectAsState()
    val name = foodState.name
    val image = foodState.image
    val price = foodState.price

    Button(onClick = { navController.popBackStack() }) {
        Text("Back")
    }

    Column {
        Text(name)
        Image(painter = painterResource(image), contentDescription = "Test")
        Text(price)
    }
}