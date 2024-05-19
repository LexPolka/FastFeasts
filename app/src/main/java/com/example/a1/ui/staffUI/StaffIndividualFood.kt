package com.example.a1.ui.staffUI

import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavHostController
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Vertices
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.core.text.isDigitsOnly
import androidx.navigation.NavHost
import coil.compose.rememberImagePainter
import com.example.a1.R
import com.example.a1.data.staffdata.IndividualFood
import com.example.a1.data.staffdata.StaffViewModel
import com.example.a1.ui.fastFeast.BackButton
import com.example.a1.ui.fastFeast.imageBitmapFromBytes
import kotlinx.coroutines.delay


@Composable
fun StaffIndividualFood(viewModel: StaffViewModel, navController : NavHostController){
    val isDarkTheme = isSystemInDarkTheme()

    val context = LocalContext.current

    //Screen settings
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp
    val addFoodButton = screenWidth * 45/100
    val componentHeight = screenHeight/10

    val universalPadding = 6.dp

    var isDialogOpen by remember { mutableStateOf(false) }

    val individualFoodList by viewModel.individualFoodList.collectAsState()

    Box(modifier = Modifier.fillMaxSize()){
        //ADD FOOD BUTTON
        IconButton(
            onClick = { isDialogOpen = true },
            modifier = Modifier
                .padding(20.dp)
                .width(addFoodButton)
                .align(Alignment.BottomEnd)
                .background(Color(0xFFFF9D7E), shape = RoundedCornerShape(20.dp))
                .border(
                    3.5.dp,
                    color = if (isDarkTheme) Color.White else Color(0xFFFDA6900),
                    shape = RoundedCornerShape(20.dp)
                )
        ) {
            Row(horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically) {
                Text(text = "Add Food to Menu", color =  if (isDarkTheme) Color.White else Color.Black, fontSize = 18.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(2.dp))
            }

        }
    }
    
    IndividualFoodList(individualFoodList, viewModel, navController)
    
    //DIALOG MANAGER
    if (isDialogOpen) {
        FoodInputDialog(
            //SAVING THE INPUT DATA AS A FOOD ITEM
            onConfirm = { foodName, foodPrice, imageUri ->
                val imageInputStream = context.contentResolver.openInputStream(imageUri)
                val imageData = imageInputStream?.readBytes()

                viewModel.addIndividualFood(foodName, foodPrice, imageData ?: byteArrayOf())
                isDialogOpen = false
            },
            onDismiss = { isDialogOpen = false }
        )
    }
}

@Composable
fun IndividualFoodList(individualFoodList : List<IndividualFood>, viewModel : StaffViewModel, navController : NavHostController){
    //LOADING IN ALL FOOD ITEMS
    
    //Screen settings
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp
    val componentHeight = screenHeight/9

    val universalPadding = 6.dp

    Column {
        BackButton(navController = navController)

        Text(
            text = "Current Menu Items",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(universalPadding) // Add bottom padding between title and items
        )
        LazyColumn(
            modifier = Modifier.padding(16.dp),
        ) {
            items(individualFoodList.size) { index ->
                var showDialog by remember { mutableStateOf(false) }

                val food = individualFoodList[index]
                val imageFromByteToBitmap = imageBitmapFromBytes(food.image)

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = universalPadding) // Adjust vertical padding
                        .border(2.dp, shape = RoundedCornerShape(20.dp), color = Color.LightGray)
                ) {
                    Column() {
                        Image(
                            bitmap = imageFromByteToBitmap,
                            contentDescription = "Food Image ${food.name}",
                            modifier = Modifier
                                .size(componentHeight)
                                .clip(shape = RoundedCornerShape(20.dp))
                                .aspectRatio(1f)
                        )
                    }
                    Column {
                        Text(text = "Name: ${food.name}", fontSize = 16.sp, modifier = Modifier.padding(universalPadding))
                        Spacer(modifier = Modifier.width(16.dp))
                        Text(text = "Price: ${food.price}", fontSize = 16.sp, modifier = Modifier.padding(universalPadding))
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        IconButton(
                            colors = IconButtonDefaults.iconButtonColors(Color.White),
                            onClick = { showDialog = true },
                            modifier = Modifier
                                .padding(universalPadding)
                                .border(3.5.dp, Color(0xFFFF9D7E), shape = CircleShape)
                                .width(componentHeight / 2)
                                .height(componentHeight / 2)
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Delete,
                                contentDescription = "Delete Food",
                                tint = Color.Red
                            )
                        }
                    }
                }

                // Dialog to confirm completion of the order
                if (showDialog) {
                    Dialog(
                        onDismissRequest = { showDialog = false },
                        properties = DialogProperties(usePlatformDefaultWidth = false)
                    ) {
                        Column (verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.fillMaxWidth().padding(8.dp).background(Color.LightGray, RoundedCornerShape(30.dp)).border(3.dp, Color.DarkGray, RoundedCornerShape(30.dp))){

                            Text("Confirm Order Completion", fontSize = 20.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(top = 4.dp))
                            Text("Are you sure you want to complete this order?", fontSize = 16.sp)

                            Row {
                                IconButton(
                                    colors = IconButtonDefaults.iconButtonColors(Color.White),
                                    onClick = { showDialog = false },
                                    modifier = Modifier
                                        .padding(8.dp)
                                        .border(3.5.dp, Color(0xFFFF9D7E), shape = CircleShape)
                                        .width(100.dp)
                                ) {
                                    Text("Cancel", color = Color(0xFFFF9D7E))
                                }
                                IconButton(
                                    colors = IconButtonDefaults.iconButtonColors(Color(0xFFFF9D7E)),
                                    onClick = {
                                        viewModel.removeIndividualFood(food)
                                        showDialog = false
                                    },
                                    modifier = Modifier
                                        .padding(8.dp)
                                        .border(3.5.dp, Color.White, shape = CircleShape)
                                        .width(100.dp)
                                ) {
                                    Text("Remove", color = Color.White)
                                }
                            }
                        }
                    }
                }

            }
        }
    }

}

@Composable
fun FoodInputDialog(onConfirm: (String, String, Uri) -> Unit, onDismiss: () -> Unit) {
    var foodName by remember { mutableStateOf("") }
    var foodPrice by remember { mutableStateOf("") }
    var imageUri by remember { mutableStateOf<Uri?>(null) }

    val context = LocalContext.current

    // Remember the launcher for activity result
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        if (uri != null) {
            imageUri = uri
        }
    }

    val universalPadding = 8.dp

    //toast handler
    var isToastVisible by remember { mutableStateOf(false) }
    if (isToastVisible) {
        LaunchedEffect(Unit) {
            delay(1000) // Adjust the delay as needed
            isToastVisible = false
        }
    }

    //CONVERT URI TO PAINTER
    var painter: Painter = rememberImagePainter(data = imageUri)

    Dialog(
        onDismissRequest = { onDismiss() },
        properties = DialogProperties(usePlatformDefaultWidth = false)
    )
    {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .background(color = Color.White, shape = RoundedCornerShape(20.dp))
                .border(3.5.dp, shape = RoundedCornerShape(20.dp), color = Color.Gray),
            verticalArrangement = Arrangement.spacedBy(universalPadding)
        ) {
            //ADD IMAGE
            Text(
                text = "Select Image",
                modifier = Modifier.padding(universalPadding),
                color = Color.Black
            )

            //CHECK IF IMAGE IS TOO BIG FOR DATABASE
            //check for blanks
            if (imageUri.toString().isNotBlank() && imageUri != null) {
                val imageToCheck = imageUri ?: Uri.EMPTY
                //uri converted to bytes
                val imageInputStream = context.contentResolver.openInputStream(imageToCheck)
                val imageData = imageInputStream?.readBytes()
                //check for size
                if (imageData?.size!! > 8000) {
                    imageUri = null
                    if (!isToastVisible) {
                        Toast.makeText(context, "Image too Large!", Toast.LENGTH_SHORT).show()
                        isToastVisible = true
                    }
                }
                else {
                    Image(
                        painter = painter,
                        contentDescription = "Food Picture",
                        modifier = Modifier
                            .size(80.dp)
                            .clickable { launcher.launch("image/*") }
                            .clip(shape = RoundedCornerShape(universalPadding))
                    )
                }
            }
            else {
                Icon(
                    imageVector = Icons.Filled.Person,
                    contentDescription = "Default Profile Picture",
                    tint = Color.Gray,
                    modifier = Modifier
                        .size(80.dp)
                        .clickable { launcher.launch("image/*") }
                        .padding(universalPadding)
                )
            }

            //FOOD NAME
            TextField(
                value = foodName,
                onValueChange = { foodName = it },
                label = { Text(text = "Food Name") },
                shape = RoundedCornerShape(20.dp),
                maxLines = 1,
                modifier = Modifier.fillMaxWidth()
            )
            //FOOD PRICE
            TextField(
                value = foodPrice,
                onValueChange = { foodPrice = it },
                label = { Text(text = "Food Price") },
                shape = RoundedCornerShape(20.dp),
                maxLines = 1,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )

            //APPLY/CANCEL
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .padding(universalPadding)
            ) {
                IconButton(
                    colors = IconButtonDefaults.iconButtonColors(Color.White),
                    onClick = { onDismiss() },
                    modifier = Modifier
                        .padding(universalPadding)
                        .border(3.5.dp, Color(0xFFFF9D7E), shape = CircleShape)
                        .width(100.dp)
                ) {
                    Text("Cancel", color = Color(0xFFFF9D7E))
                }

                IconButton(
                    colors = IconButtonDefaults.iconButtonColors(Color(0xFFFF9D7E)),
                    onClick = {
                        if (foodName.isNotBlank() && foodPrice.isNotBlank() && imageUri.toString().isNotBlank() && imageUri != null) {
                            val priceDouble = foodPrice.toDoubleOrNull()
                            if (priceDouble != null && priceDouble >= 0) {
                                onConfirm(foodName, foodPrice, imageUri ?: Uri.EMPTY)
                                onDismiss()
                            } else {
                                if (!isToastVisible) {
                                    Toast.makeText(context, "Invalid Price Input!", Toast.LENGTH_SHORT)
                                        .show()
                                    isToastVisible = true
                                }
                            }
                        } else {
                            if (!isToastVisible) {
                                Toast.makeText(context, "Fill in ALL Inputs.", Toast.LENGTH_SHORT)
                                    .show()
                                isToastVisible = true
                            }
                        }
                    },
                    modifier = Modifier
                        .padding(universalPadding)
                        .border(3.5.dp, Color.White, shape = CircleShape)
                        .width(100.dp)
                ) {
                    Text("Add", color = Color.White)
                }
            }
        }
    }
}
