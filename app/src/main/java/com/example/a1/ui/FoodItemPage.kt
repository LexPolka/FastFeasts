package com.example.a1.ui

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.a1.R
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

    //Screen settings
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val backButtonWidth = screenWidth / 2

    val universalPadding = 8.dp

    Column (modifier = Modifier.padding(universalPadding)) {
        //BACK BUTTON
        Row {
            IconButton(
                colors = IconButtonDefaults.iconButtonColors(Color(0xFFFF9D7E)),
                onClick = { navController.popBackStack() },
                modifier = Modifier
                    .border(3.5.dp, Color(0xFF975743), shape = CircleShape)
                    .width(backButtonWidth)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        tint = Color.Black,
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Back Button",
                        modifier = Modifier
                            .fillMaxHeight()
                            .padding(universalPadding)
                    )
                    Spacer(modifier = Modifier.weight(0.1f))
                    Text("Back to Main",color = Color.Black,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(universalPadding)
                    )
                }
            }
        }
        //NAME
        Row(){
            Text(text = name, color = Color.Black,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(universalPadding))
        }

        //IMAGE
        Image(painter = painterResource(image), contentDescription = "Test",
            Modifier.width(screenWidth)
                .height(screenWidth)
                .aspectRatio(1f))

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