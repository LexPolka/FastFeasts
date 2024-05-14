package com.example.a1.ui


import android.annotation.SuppressLint
import androidx.compose.foundation.Image
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
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily.Companion.SansSerif
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavHostController
import com.example.a1.FastFeastsScreen
import com.example.a1.data.cartData.CartViewModel
import com.example.a1.data.cartData.Food
import java.util.Locale


@SuppressLint("MutableCollectionMutableState")
@Composable
fun CartUi(viewModel: CartViewModel, navController : NavHostController, modifier: Modifier = Modifier){
// this is the page itself, including the buttons and cart label
    //Colours

    val lightOrange = Color(0xFFFF9D7E)
    val darkOrange = Color(0xFF975743)


    //overall cart variable
    val cart by remember {
        mutableStateOf(viewModel.cartItems)
    }

    val cartItems by remember {
        derivedStateOf { cart }
    }

    Column(
        modifier.fillMaxSize(),
        ){
        Row {

            Button(
                onClick = { navController.popBackStack() },
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

        var amountOfItemsInCart = 0

        for (x in cartItems){
            amountOfItemsInCart++
        }


        Card {

            Row {
                Column(
                    modifier = Modifier.weight(1f)
                    
                ) {
                    Text(text = "Items in Cart: $amountOfItemsInCart ")
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
        Spacer(modifier = modifier.height(20.dp))

        //pass the list of food details ( also a list )
        CartList(viewModel = viewModel, cartItems = cartItems, modifier = Modifier )

        Spacer(modifier = Modifier.weight(1f))

        Card {
            Row {
                Text(
                    text = "Total price",
                    fontSize = 30.sp,
                    fontWeight = Bold

                )

                Spacer(modifier = Modifier.weight(1f))

                val totalPrice by remember { mutableDoubleStateOf(viewModel.getTotalPrice()) }

                Text(
                    //String.format = "%.2f" to format the ,totalPrice to be displayed by the text
                    text = String.format(Locale.getDefault(), "%.2f", totalPrice ),
                    fontSize = 30.sp,
                    fontWeight = Bold
                )
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        // dining option page first, then to payment options
        Button(
            onClick = { navController.navigate(FastFeastsScreen.DiningOptions.name) },
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
fun CartList(viewModel: CartViewModel, cartItems: List<Food>, modifier: Modifier = Modifier){

    LazyColumn(
        modifier = modifier
    ) {
        items(cartItems) { food ->
            Column {
                CartItem(cartViewModel = viewModel, food = food )
                }
        }
    }
}


@Composable
fun CartItem( cartViewModel: CartViewModel, food: Food, modifier: Modifier = Modifier ){
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

                Image(painter = painterResource(food.image), contentDescription = "Image of Food")

            }

            Spacer(modifier = Modifier.width(70.dp))

            Column {

                Text(
                    text = food.price,
                    fontSize = 30.sp
                )

//                Button(
//                    onClick = { cartConfirmRemoveItemDialog(viewModel = cartViewModel, ) },
//                    elevation = ButtonDefaults.buttonElevation(
//
//                        defaultElevation = 10.dp,
//                        pressedElevation = 6.dp
//
//
//                    ),
//                    shape = CircleShape,
//                    colors = ButtonDefaults.buttonColors(darkOrange),
//                    modifier = Modifier
//                        .align(Alignment.End)
//                        .padding(16.dp)
//                        .size(35.dp)
//                ) { Text(text = "-") }


            }


        }





    }

    Spacer(modifier = Modifier.height(10.dp))

}

@Composable
fun cartConfirmRemoveItemDialog(
    viewModel: CartViewModel,
    onDismiss:()-> Unit,
    onConfirm:()-> Unit

){

    Dialog(
        onDismissRequest = { onDismiss() },
        properties = DialogProperties(usePlatformDefaultWidth = false)

    ) {
        Card {
            Column {
                Row {
                    Text(text = "Warning !")
                }
                Row {
                    Text(text = "Do you really wish to remove this item from your cart?")
                }
                Row {
                    Button(onClick = { onDismiss() }) {
                        Text(text = "Confirm")
                    }
                    Button(onClick = { onConfirm() }) {
                        Text(text = "Cancel")
                    }
                }

            }
        }
    }


}