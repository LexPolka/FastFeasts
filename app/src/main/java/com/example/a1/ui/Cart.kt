package com.example.a1.ui


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily.Companion.SansSerif
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.a1.data.AppViewModelProvider
import com.example.a1.ui.fastFeast.BackButton
import com.example.a1.ui.fastFeast.FastFeastsScreen
import com.example.a1.data.cartData.CartViewModel
import com.example.a1.data.cartData.Food
import com.example.a1.ui.fastFeast.imageBitmapFromBytes
import java.util.Locale
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.text.TextStyle


@Composable
fun CartUi(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    viewModel: CartViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val darkOrange = Color(0xFF975743)
    val darkGrey = Color(0xFF444444)

    //val foodList = viewModel.cartItems.collectAsState()

    //val cartItems = foodList.value // cartItems = List<Food>

    //overall cart variable
    val cartItems by remember { derivedStateOf { viewModel.cart } }
    //derived state that recomputes only when its dependencies change, optimize recomposition and ensure that derived values are updated correctly.

    val isEmptyChecker = false

    Column(
        modifier.fillMaxSize(),
    ) {
        BackButton(navController)

        Spacer(modifier = Modifier.height(20.dp))

        Row {
            Text(
                text = "Cart",
                fontSize = 50.sp,
                fontWeight = Bold
            )

            Spacer(modifier = Modifier.weight(1f))

            val isClearDialogShown by remember { derivedStateOf { viewModel.isClearCartDialogShown } }

            Button(
                onClick = { viewModel.onClearCartClick() },
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 10.dp,
                    pressedElevation = 6.dp
                ),
                colors = ButtonDefaults.buttonColors(darkOrange),
            ) {
                Text(
                    text = "Clear Cart",
                    fontSize = 20.sp,
                    fontFamily = SansSerif
                )
            }

            if (isClearDialogShown) {
                CartConfirmClearCartDialog(
                    onDismiss = { viewModel.onClearCartDismissClick() },
                    onConfirm = { confirmed ->
                        if (confirmed) {
                            viewModel.clearFoodCart()
                        }
                        viewModel.onClearCartDismissClick()
                    },
                )
            }
        }

        val amountOfItemsInCart = viewModel.getTotalCartItems()

        Card {
            Row(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(text = "Items in Cart: $amountOfItemsInCart")
                }
                Column {
                    Text(
                        text = "Price",
                        fontSize = 20.sp,
                        fontFamily = SansSerif,
                        fontWeight = Bold
                    )
                }
            }
        }

        Spacer(modifier = modifier.height(20.dp))

        Box {

            if (cartItems.isEmpty()) {
                Column(horizontalAlignment = Alignment.CenterHorizontally)
                {
                    Text(text = "Your cart is empty",
                        modifier = Modifier.padding(16.dp),
                        style = TextStyle(
                            color = darkGrey

                        )
                    )
                }
            } else {
                CartList(viewModel = viewModel, cartItems = cartItems)
            }

            Box(modifier = Modifier.fillMaxSize()) {
                Column(
                    modifier = Modifier.align(Alignment.BottomCenter)
                ) {
                    Card {
                        Row(
                            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "Total price",
                                fontSize = 30.sp,
                                fontWeight = Bold
                            )
                            Spacer(modifier = Modifier.weight(1f))
                            val totalPrice by remember { derivedStateOf { viewModel.getTotalCartPrice() } }
                            Text(
                                text = String.format(Locale.getDefault(), "%.2f", totalPrice),
                                fontSize = 30.sp,
                                fontWeight = Bold
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Button(
                        onClick = { navController.navigate(FastFeastsScreen.DiningOptions.name) },
                        elevation = ButtonDefaults.buttonElevation(
                            defaultElevation = 10.dp,
                            pressedElevation = 6.dp
                        ),
                        colors = ButtonDefaults.buttonColors(darkOrange),
                        enabled = cartItems.isNotEmpty()
                    ) {
                        Text(
                            text = "Proceed To Payment",
                            fontSize = 20.sp,
                            fontFamily = SansSerif
                        )
                    }
                }
            }
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
fun CartItem(cartViewModel: CartViewModel, food: Food, modifier: Modifier = Modifier ){
//the card that displays individual items in the cart, will be called by the cart list(lazy col func)
    val darkOrange = Color(0xFF975743)

    //Convert ByteArray to Bitmap
    val imageFromByteToBitmap = imageBitmapFromBytes(food.image)

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

                Image(bitmap = imageFromByteToBitmap, contentDescription = "Image of Food")

            }

            Spacer(modifier = Modifier.width(70.dp))

            Column {

                Text(
                    text = food.price,
                    fontSize = 30.sp
                )

                val isRemoveDialogShown = cartViewModel.isRemoveDialogShown

                Button(
                    onClick = {
                        cartViewModel.onRemoveFromCartClick()
                    },
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

                if (isRemoveDialogShown){
                    CartConfirmRemoveItemDialog(
                        onDismiss = { cartViewModel.onRemoveFromCartDismissClick() },
                        onConfirm =
                        {confirmed ->
                            if (confirmed) {
                                cartViewModel.removeFromCart(food)
                            }
                            cartViewModel.onRemoveFromCartDismissClick()
                        }
                    )
                }

            }
        }
    }
    Spacer(modifier = Modifier.height(10.dp))
}


@Composable
fun CartConfirmRemoveItemDialog(
    onDismiss: () -> Unit,
    onConfirm: (Boolean) -> Unit
){

    Dialog(
        onDismissRequest = { onDismiss() },
        properties = DialogProperties(usePlatformDefaultWidth = false)

    ) {
        Card(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row {
                    Text(text = "Warning !"
                    )
                }
                Row {
                    Text(" ",
                        Modifier.fillMaxWidth())
                }
                Row {
                    Text(text = "Do you wish to remove this item?")
                }
                Row {
                    Button(onClick = { onConfirm(true) }) {
                        Text(text = "Confirm")
                    }

                    Button(onClick = { onDismiss() }) {
                        Text(text = "Cancel")
                    }
                }

            }
        }
    }


}

@Composable
fun CartConfirmClearCartDialog(
    onDismiss: () -> Unit,
    onConfirm: (Boolean) -> Unit
){
    Dialog(
        onDismissRequest = { onDismiss() },
        properties = DialogProperties(usePlatformDefaultWidth = false)

    ) {
        Card {
            Column(
                modifier = Modifier,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row {
                    Text(text = "Warning !"
                    )
                }
                Row {
                    Text(" ",
                        Modifier.fillMaxWidth())
                }
                Row {
                    Text(text = "Do you really wish to clear your cart?")
                }
                Row {
                    Button(onClick = { onConfirm(true) },
                        elevation = ButtonDefaults.buttonElevation(
                            defaultElevation = 10.dp,
                            pressedElevation = 6.dp
                        ),) {
                        Text(text = "Confirm")
                    }

                    Button(onClick = { onDismiss() }) {
                        Text(text = "Cancel")
                    }
                }

            }
        }
    }


}