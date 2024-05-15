package com.example.a1.ui.staffUI

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.a1.FastFeastsScreen
import com.example.a1.R

@Composable
fun StaffPage(navController : NavHostController){
    val isDarkTheme = isSystemInDarkTheme()

    //Screen settings
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp
    val componentHeight = screenHeight / 9

    val imageSize = 48.dp
    val textSize = 16.sp
    val linePadding = 12.dp

    Column {
        //Look at Orders
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = androidx.compose.ui.Modifier
                .height(componentHeight)
                .padding(linePadding)
        ) {
            Image(
                painter = painterResource(R.drawable.baseline_assignment_ind_24), contentDescription = "Name",
                Modifier
                    .size(imageSize)
                    .padding(6.dp)
                    .clip(shape = CircleShape)
                    .background(Color.White)
            )

            Text(text = "Check Pending Orders", color = if (isDarkTheme) Color.White else Color.Black)

            Spacer(modifier = Modifier.weight(1f))
        }

        Divider(
            thickness = 2.dp,
            color = Color(0xFFFC8803),
            modifier = Modifier.padding(horizontal = linePadding)
        )

        //Look at Stocks
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .height(componentHeight)
                .padding(linePadding)
        ) {
            Image(
                painter = painterResource(R.drawable.baseline_assignment_ind_24), contentDescription = "Name",
                Modifier
                    .size(imageSize)
                    .padding(6.dp)
                    .clip(shape = CircleShape)
                    .background(Color.White)
            )

            Text(text = "Manage Stock", color = if (isDarkTheme) Color.White else Color.Black)

            Spacer(modifier = Modifier.weight(1f))
        }

        Divider(
            thickness = 2.dp,
            color = Color(0xFFFC8803),
            modifier = Modifier.padding(horizontal = linePadding)
        )

        //Add Individual Items
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .height(componentHeight)
                .padding(linePadding)
                .clickable {
                    navController.navigate(FastFeastsScreen.StaffIndividualFood.name)
                }
        ) {
            Image(
                painter = painterResource(R.drawable.baseline_assignment_ind_24), contentDescription = "Name",
                Modifier
                    .size(imageSize)
                    .padding(6.dp)
                    .clip(shape = CircleShape)
                    .background(Color.White)
            )

            Text(text = "Add/Delete Menu Item", color = if (isDarkTheme) Color.White else Color.Black)

            Spacer(modifier = Modifier.weight(1f))
        }

        Divider(
            thickness = 2.dp,
            color = Color(0xFFFC8803),
            modifier = Modifier.padding(horizontal = linePadding)
        )
    }

}