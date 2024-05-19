package com.example.a1.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
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
import com.example.a1.data.profiledata.ProfileViewModel
import com.example.a1.ui.fastFeast.BackButton

@Composable
fun PaymentOptions(
    viewModel: ProfileViewModel,
    navController: NavHostController,
    modifier: Modifier = Modifier
){
    val lightOrange = Color(0xFFFF9D7E)
    val creditCardBindCheck = remember { viewModel.checkCreditCard() }

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
                Button(
                onClick = {
                    if (creditCardBindCheck) {
                        navController.navigate(FastFeastsScreen.OnlineBanking.name)
                    }
                },
                enabled = creditCardBindCheck,
                modifier =
                Modifier.size(150.dp, 150.dp)
                ) {
                Image(
                    painter = painterResource(R.drawable.onlinebankingimg),
                    contentDescription = "Online Banking",
                    modifier = Modifier
                        .size(150.dp)
                        .weight(1f)
                )
                }
                Text(
                    text = "Online Banking",
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.height(20.dp))

            Card {
                Button(
                    onClick = {
                        navController.navigate(FastFeastsScreen.PayAtCounter.name)

                    },
                    modifier =
                    Modifier.size(150.dp, 150.dp)
                ) {
                        Image(
                            painter = painterResource(R.drawable.paywithcashimg),
                            contentDescription = "Pay at Counter",
                            modifier = Modifier
                                .size(150.dp)
                                .fillMaxSize()
                        )
                }
                Text(
                    text = "Pay at Counter",
                    fontWeight = FontWeight.Bold
                )
            }


            Spacer(modifier = Modifier.height(50.dp))

        }
    }


}







