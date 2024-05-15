package com.example.a1.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.a1.ui.fastFeast.FastFeastsScreen
import com.example.a1.R

@Composable
fun DiningOptions(
    navController: NavController,
    modifier: Modifier = Modifier
)
{
        val lightOrange = Color(0xFFFF9D7E)

        Button(
            onClick = { navController.popBackStack() },
            elevation = ButtonDefaults.buttonElevation(
                defaultElevation = 10.dp,
                pressedElevation = 6.dp
            ),
            colors = ButtonDefaults.buttonColors(lightOrange)
        ) {
            Text(
                text = "< Back To Cart",
                fontSize = 20.sp,
                fontFamily = FontFamily.SansSerif
            )
        }


        Column(
            modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally)
        {
            Spacer(modifier = Modifier.height(50.dp))

            Text(
                text = "Select Dining Options",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold

            )

            Spacer(modifier = Modifier.height(20.dp))

            Card {
                Column {
                    Image(
                        painter = painterResource(R.drawable.dineinimage),
                        contentDescription = "Dine in",
                        modifier
                            .size(150.dp)
                            .clickable { navController.navigate(FastFeastsScreen.PaymentOptions.name) }

                    )
                    Text(
                        text = "Dine in",
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Card {
                Column {
                    Image(
                        painter = painterResource(R.drawable.takeoutimg),
                        contentDescription = "Take Out",
                        modifier
                            .size(150.dp)
                            .clickable { navController.navigate(FastFeastsScreen.PaymentOptions.name) }
                    )
                    Text(
                        text = "Take Out",
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))


        }

}
