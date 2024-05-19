package com.example.a1.ui.staffUI

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.ui.Alignment
import com.example.a1.data.CustomizeFood.StockEntity
import com.example.a1.data.CustomizeFood.StockViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StaffStocks(viewModel: StaffViewModel, stockViewModel: StockViewModel, navController: NavHostController) {
    val isDarkTheme = isSystemInDarkTheme()
    //Screen settings
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp
    val componentHeight = screenHeight / 9

    val universalPadding = 6.dp

    //ALL THE INGREDIENTS ARE STORED IN THIS DATABASE
    val allIngredients by stockViewModel.allIngredients.collectAsState()
    //

    var name by remember { mutableStateOf("") }
    var quantity by remember { mutableStateOf("") }






        Column {
            BackButton(navController = navController)

            Text(
                text = "Stock Manager",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(universalPadding)
            )
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Name") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = quantity,
                onValueChange = { quantity = it },
                label = { Text("Quantity") },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    val quantityInt = quantity.toIntOrNull()
                    if (quantityInt != null) {
                        stockViewModel.SaveIngredient(name = name, quantity = quantityInt)
                    }
                },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text("Insert Data")
            }
            LazyColumn(modifier = Modifier.padding(16.dp))
            {

                //FOR EVERY ITEM ON BURGER LIST
                items(allIngredients.size) { index ->
                    var stockQuantity by remember { mutableStateOf(allIngredients[index].quantity) }

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(universalPadding)
                    ) {
                        Text(
                            text = allIngredients[index].name,
                            fontSize = 18.sp,
                            modifier = Modifier.padding(universalPadding)
                        )

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .width(100.dp)
                            ) {
                                Image(
                                    painter = painterResource(id = when(index){
                                            0 -> R.drawable.burger_buns
                                            1 -> R.drawable.pretzel_buns
                                            2 -> R.drawable.wheat_bun
                                            3 -> R.drawable.patty1
                                            4 -> R.drawable.beefpatty
                                            5 -> R.drawable.baconpatty
                                            6 -> R.drawable.iceberg_lettuceinmenu
                                            7 -> R.drawable.romaine_lettuceinmenu
                                            8 -> R.drawable.tomatosauceinmenu
                                            9 -> R.drawable.chillisauceinmenu
                                            10 -> R.drawable.bbqsauceinmenu
                                            11 -> R.drawable.mustardsauceinmenu
                                            12 -> R.drawable.egg
                                            13 -> R.drawable.cheese
                                            else -> R.drawable.emptyslot
                                    }),
                                    contentDescription = "${allIngredients[index].name} Image",
                                    modifier = Modifier.fillMaxWidth()
                                )
                            }
                            Column {
                                //button row and textfield
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.Center
                                ) {
                                    TextField(
                                        value = stockQuantity.toString(),
                                        onValueChange = { newValue ->
                                            stockQuantity =
                                                if (newValue.isBlank()) 0 else newValue.toInt()
                                            // REMEMBER TO UPDATE /SAVE data
                                            allIngredients[index].quantity = stockQuantity
                                        },
                                        label = { Text(text = "Quantity") },
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
                                            .background(
                                                Color.Transparent,
                                                shape = RoundedCornerShape(16.dp)
                                            )
                                    )

                                    Button(onClick = { stockViewModel.SetQuantity(allIngredients[index].id, stockQuantity)
                                    }){
                                        Text(text = "Apply Changes")
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

