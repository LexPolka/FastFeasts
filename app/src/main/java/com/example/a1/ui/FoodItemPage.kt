package com.example.a1.ui

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import com.example.a1.data.cartData.CartViewModel
import com.example.a1.data.cartData.Food
import com.example.a1.data.profiledata.GlobalViewModel

@Composable
fun InvididualFoodPage(cartViewModel : CartViewModel, globalViewModel: GlobalViewModel, navController: NavHostController) {
    val foodState by globalViewModel.foodState.collectAsState()
    val name = foodState.name
    val image = foodState.image
    val price = foodState.price

    val context = LocalContext.current

    Column {
        Button(onClick = { navController.popBackStack() }) {
            Text("Back")
        }
        Text(name)
        Image(painter = painterResource(image), contentDescription = "Test")
        Text(price)

        //ORDER
        Button(onClick = {
            val food = Food(name = name, image = image, price = price)
            cartViewModel.addToCart(food)
            Toast.makeText(context, "Added to Cart.", Toast.LENGTH_SHORT).show()
        }) {
            Text("Order")
        }
    }


}