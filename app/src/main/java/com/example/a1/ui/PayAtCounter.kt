package com.example.a1.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.a1.ui.fastFeast.FastFeastsScreen
import com.example.a1.data.cartData.CartViewModel
import java.util.Locale

@Composable
fun PayAtCounterUi(
    viewModel: CartViewModel,
    navController: NavController
){

    val darkOrange = Color(0xFF975743)

    val referenceNumber = (200..400).random()
    val receiptItems = viewModel.cartItems
    val totalPrice = viewModel.getTotalPrice()

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {

        Column {
            Text("Thanks for Dining at")
            Text("     FastFeasts !    ")
        }

        Text("Please proceed to the counter to pay for your food")
        Text("Reference Number: $referenceNumber")
        Spacer(Modifier.height(15.dp))

        Card { ReceiptList(receiptItems = receiptItems, modifier = Modifier ) }

        Spacer(Modifier.weight(1f))
        Box{
            //totalPrice & back to main menu button
            Column {

                Spacer(modifier = Modifier.height(10.dp))

                Card {
                    Row(
                        Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {

                        Text("Total Price: ")
                        Spacer(modifier = Modifier.weight(1f))
                        Text(text = String.format(Locale.getDefault(), "%.2f", totalPrice ))

                    }
                }

                Button(
                    onClick = { navController.navigate(FastFeastsScreen.MainPage.name) },
                    elevation = ButtonDefaults.buttonElevation(
                        defaultElevation = 10.dp,
                        pressedElevation = 6.dp
                    ),
                    colors = ButtonDefaults.buttonColors(darkOrange)
                ) {

                    Text(
                        text = "Return to Main Menu",
                        fontSize = 20.sp,
                        fontFamily = FontFamily.SansSerif

                    )

                }
            }

        }


    }

}


