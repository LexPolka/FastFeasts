package com.example.a1.ui.staffUI

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.a1.R
import com.example.a1.data.staffdata.StaffViewModel
import com.example.a1.ui.fastFeast.BackButton
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontStyle
import com.example.a1.data.staffdata.Stock

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StaffStocks(viewModel: StaffViewModel, navController: NavHostController) {
    val isDarkTheme = isSystemInDarkTheme()

    //Screen settings
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp
    val componentHeight = screenHeight / 9

    val universalPadding = 6.dp

    //TEMPORARY!! ALLOY PLS REMOVE THIS AND REPLACE WITH ACTUAL DATA \/
    var burgerList = mutableListOf<Stock>()
    burgerList.addAll(
        listOf(
            Stock(name = "Bun", quantity = 10, image = R.drawable.burger_buns),
            Stock(name = "Patty", quantity = 5, image = R.drawable.pattyicon),
            Stock(name = "Lettuce", quantity = 20, R.drawable.lettuceicon)
        )
    )
    //TEMPORARY!! ALLOY PLS REMOVE THIS AND REPLACE WITH ACTUAL DATA /\


    Column {
        BackButton(navController = navController)

        Text(
            text = "Stock Manager",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(universalPadding)
        )
        LazyColumn(modifier = Modifier.padding(16.dp))
        {
            //FOR EVERY ITEM ON BURGER LIST
            items(burgerList.size) { index ->
                var stockQuantity by remember { mutableStateOf(burgerList[index].quantity)}

                Card (modifier = Modifier
                    .fillMaxWidth()
                    .padding(universalPadding)) {
                    Text(text = burgerList[index].name, fontSize = 18.sp, modifier = Modifier.padding(universalPadding))

                    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
                        Column (modifier = Modifier
                            .fillMaxHeight()
                            .width(100.dp)) {
                            Image(painter = painterResource(id = burgerList[index].image), contentDescription = "${burgerList[index].name} Image", modifier = Modifier.fillMaxWidth())
                        }
                        Column {
                            //button row and textfield
                            Row (modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center){
                                TextField(
                                    value = stockQuantity.toString(),
                                    onValueChange = { newValue ->
                                        stockQuantity = if (newValue.isBlank()) 0 else newValue.toInt()
                                        // REMEMBER TO UPDATE /SAVE data
                                        burgerList[index].quantity = stockQuantity
                                    },
                                    label = { Text(text = "Quantity") } ,
                                    maxLines = 1,
                                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                                    colors = TextFieldDefaults.textFieldColors(
                                        focusedIndicatorColor = Color(0xFFFDA6900), // Color when the TextField is focused
                                        unfocusedIndicatorColor = Color(0xFFFDA6900), // Color when the TextField is not focused
                                        cursorColor = Color(0xFFFF9D7E), // Color of the cursor
                                        containerColor = if (isDarkTheme) Color.DarkGray else Color.White,
                                    ),
                                    modifier = Modifier
                                        .fillParentMaxWidth(0.3f)
                                        .padding(2.dp)
                                        .border(
                                            1.5.dp,
                                            color = Color.DarkGray,
                                            shape = RoundedCornerShape(16.dp)
                                        )
                                        .background(Color.Transparent, shape = RoundedCornerShape(16.dp))
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
