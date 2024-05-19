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
import androidx.navigation.NavHostController
import com.example.a1.ui.fastFeast.FastFeastsScreen
import com.example.a1.R
import com.example.a1.ui.fastFeast.BackButton

@Composable
fun PaymentOptions(
    navController: NavHostController,
    modifier: Modifier = Modifier
){
    val lightOrange = Color(0xFFFF9D7E)

    Column {

        BackButton(navController)

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
                            .clickable { navController.navigate(FastFeastsScreen.OnlineBanking.name) }
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
                            .clickable { navController.navigate(FastFeastsScreen.PayAtCounter.name) }

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







