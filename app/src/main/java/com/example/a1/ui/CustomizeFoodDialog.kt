package com.example.test

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.a1.R

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CustomBunDialog(
    onDismiss: () -> Unit,
    onConfirm: (Int) -> Unit

){
    val viewModel: FoodMenuViewModel = viewModel()
    Dialog(
        onDismissRequest = { onDismiss() },
        properties = DialogProperties(usePlatformDefaultWidth = false)
    )
    {
        androidx.compose.material3.Card(
            shape = RoundedCornerShape(15.dp),
            modifier = Modifier
                .padding(15.dp)
        )

        {
            FlowRow(
                maxItemsInEachRow = 3,
                horizontalArrangement = Arrangement.Center,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            )
            {

                Image(
                    painter = painterResource(id = R.drawable.burger_buns),
                    contentDescription = "CLASSIC BUN",
                    modifier = Modifier
                        .size(150.dp)
                        .clickable {
                            val addedBuns = viewModel.Buns(name = "Classic Bun", price = 1.50, image = R.drawable.burger_buns)
                            viewModel.addedNewBun(newBun = addedBuns)
                            onConfirm(R.drawable.burger_buns)
                            onDismiss()
                        }
                )
                Image(
                    painter = painterResource(id = R.drawable.pretzel_buns),
                    contentDescription = "CLASSIC BUN",
                    modifier = Modifier
                        .size(150.dp)
                        .clickable {
                            val addedBuns = viewModel.Buns(name = "Pretzel Buns", price = 1.80, image = R.drawable.pretzel_buns)
                            viewModel.addedNewBun(newBun = addedBuns)
                            onConfirm(R.drawable.pretzel_buns)
                            onDismiss()
                        }
                )
            }
        }

    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CustomPattyDialog(
    onDismiss: () -> Unit,
    onConfirm: (Int) -> Unit

){
    val viewModel: FoodMenuViewModel = viewModel()
    Dialog(
        onDismissRequest = { onDismiss() },
        properties = DialogProperties(usePlatformDefaultWidth = false)
    )
    {
        androidx.compose.material3.Card(
            shape = RoundedCornerShape(15.dp),
            modifier = Modifier
                .padding(15.dp)
        )

        {
            FlowRow(
                maxItemsInEachRow = 3,
                horizontalArrangement = Arrangement.Center,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            )
            {

                Image(
                    painter = painterResource(id = R.drawable.patty1),
                    contentDescription = "CHICKEN PATTY",
                    modifier = Modifier
                        .size(150.dp)
                        .clickable {
                            val addedPatty = viewModel.Patty(name = "Chicken Patty", price = 3.50, image = R.drawable.patty1)
                            viewModel.addedNewPatty(newPatty = addedPatty)
                            onConfirm(R.drawable.patty1)
                            onDismiss()
                        }
                )
                Image(
                    painter = painterResource(id = R.drawable.beefpatty),
                    contentDescription = "BEEF PATTY",
                    modifier = Modifier
                        .size(150.dp)
                        .clickable {
                            val addedPatty = viewModel.Patty(name = "Beef Patty", price = 4.50, image = R.drawable.beefpatty)
                            viewModel.addedNewPatty(newPatty = addedPatty)
                            onConfirm(R.drawable.beefpatty)
                            onDismiss()
                        }
                )


            }
        }

    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CustomLettuceDialog(
    onDismiss: () -> Unit,
    onConfirm: (Int) -> Unit

){
    val viewModel: FoodMenuViewModel = viewModel()
    Dialog(
        onDismissRequest = { onDismiss() },
        properties = DialogProperties(usePlatformDefaultWidth = false)
    )
    {
        androidx.compose.material3.Card(
            shape = RoundedCornerShape(15.dp),
            modifier = Modifier
                .padding(15.dp)
        )

        {
            FlowRow(
                maxItemsInEachRow = 3,
                horizontalArrangement = Arrangement.Center,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            )
            {

                Image(
                    painter = painterResource(id = R.drawable.iceberg_lettuceinmenu),
                    contentDescription = "ICEBERG LETTUCE",
                    modifier = Modifier
                        .size(150.dp)
                        .clickable {
                            val addedLettuce = viewModel.Lettuce(name = "Iceberg Lettuce", price = 1.00, image = R.drawable.iceberg_lettuce)
                            viewModel.addedNewLettuce(newLettuce = addedLettuce)
                            onConfirm(R.drawable.iceberg_lettuce)
                            onDismiss()
                        }
                )
            }
        }

    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CustomSauceDialog(
    onDismiss: () -> Unit,
    onConfirm: (Int) -> Unit

){
    val viewModel: FoodMenuViewModel = viewModel()
    Dialog(
        onDismissRequest = { onDismiss() },
        properties = DialogProperties(usePlatformDefaultWidth = false)
    )
    {
        androidx.compose.material3.Card(
            shape = RoundedCornerShape(15.dp),
            modifier = Modifier
                .padding(15.dp)
        )

        {
            FlowRow(
                maxItemsInEachRow = 3,
                horizontalArrangement = Arrangement.Center,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            )
            {

                Image(
                    painter = painterResource(id = R.drawable.tomatosauceinmenu),
                    contentDescription = "TOMATO SAUCE",
                    modifier = Modifier
                        .size(150.dp)
                        .clickable {
                            val addedSauce = viewModel.Sauce(name = "Tomato Sauce", price = 0.20, image = R.drawable.tomato_sauce)
                            viewModel.addedNewSauce(newSauce = addedSauce)
                            onConfirm(R.drawable.tomatosauceinmenu)
                            onDismiss()
                        }
                )
                Image(
                    painter = painterResource(id = R.drawable.mustardsauceinmenu),
                    contentDescription = "MUSTARD SAUCE",
                    modifier = Modifier
                        .size(150.dp)
                        .clickable {
                            val addedSauce = viewModel.Sauce(name = "Mustard Sauce", price = 0.20, image = R.drawable.mustard_sauce)
                            viewModel.addedNewSauce(newSauce = addedSauce)
                            onConfirm(R.drawable.mustard_sauce)
                            onDismiss()
                        }
                )


            }
        }

    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CustomExtraDialog(
    onDismiss: () -> Unit,
    onConfirm: (Int) -> Unit

){
    val viewModel: FoodMenuViewModel = viewModel()
    Dialog(
        onDismissRequest = { onDismiss() },
        properties = DialogProperties(usePlatformDefaultWidth = false)
    )
    {
        androidx.compose.material3.Card(
            shape = RoundedCornerShape(15.dp),
            modifier = Modifier
                .padding(15.dp)
        )

        {
            FlowRow(
                maxItemsInEachRow = 3,
                horizontalArrangement = Arrangement.Center,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            )
            {

                Image(
                    painter = painterResource(id = R.drawable.egg),
                    contentDescription = "CHICKEN PATTY",
                    modifier = Modifier
                        .size(150.dp)
                        .clickable {
                            val addedExtra = viewModel.Extra(name = "Egg", price = 1.00, image = R.drawable.egg)
                            viewModel.addedNewExtra(newExtra = addedExtra)
                            onConfirm(R.drawable.egg)
                            onDismiss()
                        }
                )
                Image(
                    painter = painterResource(id = R.drawable.cheese),
                    contentDescription = "CHEESE",
                    modifier = Modifier
                        .size(150.dp)
                        .clickable {
                            val addedExtra = viewModel.Extra(name = "Cheese", price = 1.00, image = R.drawable.cheese)
                            viewModel.addedNewExtra(newExtra = addedExtra)
                            onConfirm(R.drawable.cheese)
                            onDismiss()
                        }
                )


            }
        }

    }
}