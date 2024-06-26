package com.example.a1.ui.fastFeast

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.a1.R
import com.example.a1.data.cartData.CartViewModel
import com.example.a1.data.cartData.Food
import com.example.a1.data.profiledata.GlobalViewModel
import kotlinx.coroutines.delay
import java.math.BigDecimal
import java.math.BigInteger

@Composable
fun IndividualFoodPage(cartViewModel : CartViewModel, globalViewModel: GlobalViewModel, navController: NavHostController) {
    val isDarkTheme = isSystemInDarkTheme()

    val foodState by globalViewModel.foodState.collectAsState()
    val name = foodState.name
    val image = foodState.image
    val price = foodState.price

    val context = LocalContext.current

    //Screen settings
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val viewCartWidth = screenWidth * 45/100

    //toast handler
    var isToastVisible by remember { mutableStateOf(false) }
    if (isToastVisible) {
        LaunchedEffect(Unit) {
            delay(500) // Adjust the delay as needed
            isToastVisible = false
        }
    }
    //Back button handler
    var isBackPressed by remember { mutableStateOf(false) }
    if (isBackPressed) {
        LaunchedEffect(Unit) {
            delay(2000) // Adjust the delay as needed
            isBackPressed = false
        }
    }

    val universalPadding = 8.dp

    var quantity by remember { mutableStateOf(1) }

    //Convert ByteArray to Bitmap
    val imageFromByteToBitmap = imageBitmapFromBytes(image)

    Column (modifier = Modifier.padding(universalPadding)) {
        //BACK BUTTON
        BackButton(navController = navController)

        Card(modifier = Modifier.padding(universalPadding))
        {
            //NAME
            Row(){
                Text(text = name, color = if (isDarkTheme) Color.White else Color.Black,
                    fontSize = 24.sp,
                    modifier = Modifier.padding(universalPadding))
            }


            //IMAGE
            Image(bitmap = imageFromByteToBitmap, contentDescription = "Test",
                Modifier
                    .aspectRatio(1f)
                    .width(screenWidth/2)
                    .height(screenWidth/2)
            )

            //PRICE
            Text( "Total: $${calcPrice(price, quantity)}",
                color = if (isDarkTheme) Color.White else Color.Black,
                fontSize = 20.sp,
                modifier = Modifier.padding(universalPadding))
        }

        Row(verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(universalPadding)
        ){
            //ORDER
            Button(onClick = {
                for (i in 0 until quantity)
                {
                    val food = Food(name = name, image = image, price = price)
                    cartViewModel.addToCart(food)
                    if (!isToastVisible)
                    {
                        Toast.makeText(context, "Added ${quantity} ${name} Cart.", Toast.LENGTH_SHORT).show()
                        isToastVisible = true
                    }
                }
            },
                colors = ButtonDefaults.buttonColors(Color(0xFFFF9D7E)),
                modifier = Modifier.height(48.dp)
            )
            {
                Text("Order",
                    color = if (isDarkTheme) Color.White else Color.Black,
                    fontSize = 20.sp
                )
            }
            //DECREASE
            Button(
                onClick = { if (quantity > 1) quantity -= 1 },
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors(Color(0xFFFF9D7E))
            ) {
                Text("-",
                    color = if (isDarkTheme) Color.White else Color.Black,
                    fontSize = 20.sp
                )
            }

            Text(text = quantity.toString(),
                color = if (isDarkTheme) Color.White else Color.Black,
                fontSize = 20.sp
            )

            //INCREASE
            Button(
                onClick = { if (quantity < 10) quantity += 1 },
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors(Color(0xFFFF9D7E))
            ) {
                Text("+",
                    color = if (isDarkTheme) Color.White else Color.Black,
                    fontSize = 20.sp
                )
            }
        }

        Box (Modifier.fillMaxSize()) {
            // VIEW CART BUTTON
            IconButton(
                onClick = { navController.navigate(FastFeastsScreen.Cart.name) },
                modifier = Modifier
                    .padding(20.dp)
                    .width(viewCartWidth)
                    .align(Alignment.BottomEnd)
                    .background(Color(0xFFFF9D7E), shape = RoundedCornerShape(20.dp))
                    .border(
                        3.5.dp,
                        color = if (isDarkTheme) Color.White else Color(0xFFFDA6900),
                        shape = RoundedCornerShape(20.dp)
                    )
            ) {
                Row(horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically) {
                    Spacer(Modifier.weight(0.3f))
                    Icon(
                        painter = painterResource(R.drawable.cart),
                        contentDescription = "View Cart",
                        tint = Color.White,
                    )
                    Spacer(Modifier.weight(0.3f))
                    Text(text = "View Cart", color =  if (isDarkTheme) Color.White else Color.Black, fontSize = 18.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(2.dp))
                    Spacer(Modifier.weight(0.3f))
                }

            }
        }
    }
}

fun calcPrice(priceString: String, quantity: Int): BigDecimal {
    val unitPrice = BigDecimal(priceString)
    val qty = BigInteger.valueOf(quantity.toLong())

    val totalPrice = unitPrice * BigDecimal(qty)
    return totalPrice
}