package com.example.a1.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily.Companion.SansSerif
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import java.util.Locale


@Composable
fun CartUi(viewModel: CartViewModel, modifier: Modifier = Modifier){
// this is the page itself, including the buttons and cart label
    //Colours

    val lightOrange = Color(0xFFFF9D7E)
    val darkOrange = Color(0xFF975743)


//get dummy data from viewmodel

    viewModel.addToCart(Food(1,"Fries", "5.00"))
    viewModel.addToCart(Food(2,"Burger", "7.00"))

    val cartItems = listOf( viewModel.getCartItems() )

    Column(
        modifier.fillMaxSize(),

        ){
        Row {
            Button(
                onClick = { /*TODO*/ },
                elevation = ButtonDefaults.buttonElevation(

                    defaultElevation = 10.dp,
                    pressedElevation = 6.dp


                ),
                colors = ButtonDefaults.buttonColors(lightOrange)
            ) {
                Text(
                    text = "< Not finished ? Keep Ordering",
                    fontSize = 20.sp,
                    fontFamily = SansSerif

                )

            }

        }

        Spacer(modifier = Modifier.height(20.dp))

        Row {
            Text(
                text = "Cart",
                fontSize = 50.sp,
                fontWeight = Bold

            )

            Spacer(modifier = Modifier.weight(1f))

            Button(onClick = { viewModel.clearCart() },elevation = ButtonDefaults.buttonElevation(

                defaultElevation = 10.dp,
                pressedElevation = 6.dp


            ),
                colors = ButtonDefaults.buttonColors(darkOrange),
                modifier = Modifier
                    .padding(16.dp)
            ) {
                Text(text = "Clear Cart",
                    fontSize = 20.sp,
                    fontFamily = SansSerif
                )
            }
        }

        Card {

            Row {
                Column(
                    modifier = Modifier.weight(1f)

                ) {

                }
                Column{
                    Text(text = "Price",
                        fontSize = 20.sp,
                        fontFamily = SansSerif,
                        fontWeight = Bold
                    )
                }

            }


        }


        //pass the list of food details ( also a list )
        CartList(viewModel = viewModel, cartItems = cartItems, modifier = Modifier )

        val totalPrice: Double = cartItems.flatten().sumOf { item ->
            // Convert the price string to double
            item.price.toDoubleOrNull() ?: 0.0
        }

        Spacer(modifier = Modifier.weight(1f))

        Card {
            Row {
                Text(
                    text = "Total price",
                    fontSize = 30.sp,
                    fontWeight = Bold

                )

                Spacer(modifier = Modifier.weight(1f))

                Text(
                    text = String.format(Locale.getDefault(), "%.2f", totalPrice),
                    fontSize = 30.sp,
                    fontWeight = Bold
                )
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        // dining option page fist, then to payment options
        Button(
            onClick = { /*TODO*/ },
            elevation = ButtonDefaults.buttonElevation(

                defaultElevation = 10.dp,
                pressedElevation = 6.dp


            ),
            colors = ButtonDefaults.buttonColors(darkOrange)
        ) {

            Text(
                text = "Proceed To Payment",
                fontSize = 20.sp,
                fontFamily = SansSerif

            )

        }
    }



}

@Composable
fun CartList(viewModel: CartViewModel, cartItems: List<List<Food>>, modifier: Modifier = Modifier){


    LazyColumn(
        modifier = modifier
    ) {
        items(cartItems) { foodList ->
            Column {
                foodList.forEach{
                        food -> CartItem(viewModel = viewModel, food = food)
                }
            }
        }
    }
}


@Composable
fun CartItem(viewModel: CartViewModel, food: Food, modifier: Modifier = Modifier ){
//the card that displays individual items in the cart, will be called by the cart list(lazy col func)
    val darkOrange = Color(0xFF975743)
    Card(
        modifier.size(width = 400.dp, height = 100.dp)

    ){
        Row {

            Column(
                modifier = Modifier.weight(1f)
            ) {

                Text(
                    text = food.name,
                    fontSize = 30.sp
                )

                Text(
                    text = "${food.image}"

                )
            }

            Spacer(modifier = Modifier.width(70.dp))

            Column {

                Text(
                    text = food.price,
                    fontSize = 30.sp
                )

                Button(
                    onClick = { viewModel.removeFromCart(food) },
                    elevation = ButtonDefaults.buttonElevation(

                        defaultElevation = 10.dp,
                        pressedElevation = 6.dp


                    ),
                    shape = CircleShape,
                    colors = ButtonDefaults.buttonColors(darkOrange),
                    modifier = Modifier
                        .align(Alignment.End)
                        .padding(16.dp)
                        .size(35.dp)
                ) { Text(text = "-") }


            }


        }





    }

    Spacer(modifier = Modifier.height(10.dp))

}



/*@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Test2Theme {
        CartUi(
            Modifier.fillMaxSize()
        )
    }
}*/