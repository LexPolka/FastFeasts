package com.example.a1.ui

import androidx.compose.foundation.Image
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.a1.R

@Composable
fun PaymentOptions(
    navController: NavController,
    modifier: Modifier = Modifier
){
    val lightOrange = Color(0xFFFF9D7E)

    Column {

        Button(
            onClick = { navController.popBackStack() },
            elevation = ButtonDefaults.buttonElevation(
                defaultElevation = 10.dp,
                pressedElevation = 6.dp
            ),
            colors = ButtonDefaults.buttonColors(lightOrange)
        ) {
            Text(
                text = "< Back ",
                fontSize = 20.sp,
                fontFamily = FontFamily.SansSerif
            )
        }

        Column(

            modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,


            ) {
            Spacer(modifier = Modifier.height(50.dp))

            Text(
                text = "Select Payment Option",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold

            )
            Spacer(modifier = Modifier.height(20.dp))

            Card {
                Column {
                    Image(
                        painter = painterResource(R.drawable.onlinebankingimg),
                        contentDescription = "Online Banking",
                        modifier
                            .size(150.dp)

                    )
                    Text(
                        text = "Online Banking",
                        fontWeight = FontWeight.Bold
                    )
                }
            }
            Spacer(modifier = Modifier.height(20.dp))

            Card {
                Column {
                    Image(
                        painter = painterResource(R.drawable.paywithcashimg),
                        contentDescription = "Pay at Counter",
                        modifier
                            .size(150.dp)

                    )
                    Text(
                        text = "Pay with Cash",
                        fontWeight = FontWeight.Bold
                    )

                }
            }


            Spacer(modifier = Modifier.height(50.dp))

        }
    }


}







