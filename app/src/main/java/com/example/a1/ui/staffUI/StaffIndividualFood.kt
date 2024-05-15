package com.example.a1.ui.staffUI

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import com.example.a1.FastFeastsScreen
import com.example.a1.R
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.core.net.toUri
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.example.a1.data.staffdata.IndividualFood
import com.example.a1.data.staffdata.StaffViewModel
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
        // VIEW CART BUTTON
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

    //LOADING IN ALL FOOD ITEMS
    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(text = "Current Menu Items", fontSize = 20.sp, fontWeight = FontWeight.Bold)

        individualFoodList.forEach {food ->

            //CONVERT STRING TO PAINTER
            val painter: Painter = rememberImagePainter(data = food.image)

            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(universalPadding)
                .border(2.dp, shape = RoundedCornerShape(20.dp), color = Color.LightGray)
            ) {
                Column(){
                    Image(painter = painter, contentDescription = "Image", modifier = Modifier.size(componentHeight))
                }
                Column {
                    Text(text = "Name: ${food.name}", fontSize = 16.sp, modifier = Modifier.padding(universalPadding))
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(text = "Price: ${food.price}", fontSize = 16.sp, modifier = Modifier.padding(universalPadding))
                }
            }
        }
    }

    //DIALOG MANAGER
    if (isDialogOpen) {
        FoodInputDialog(
            //SAVING THE INPUT DATA AS A FOOD ITEM
            onConfirm = { foodName, foodPrice, imageUri ->

                val imageInputStream = context.contentResolver.openInputStream(imageUri)
                val imageData = imageInputStream?.readBytes()

                viewModel.addIndividualFood(foodName, foodPrice, imageData)
                isDialogOpen = false
            },
            onDismiss = { isDialogOpen = false }
        )
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
    val painter: Painter = rememberImagePainter(data = imageUri)

    Dialog(
        onDismissRequest = { onDismiss() },
        properties = DialogProperties(usePlatformDefaultWidth = false))
    {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .background(color = Color.White, shape = RoundedCornerShape(20.dp))
                .border(3.5.dp, shape = RoundedCornerShape(20.dp), color = Color.Gray),
            verticalArrangement = Arrangement.spacedBy(universalPadding)
        ) {
            //ADD IMAGE
            Text(text = "  Select Image", modifier = Modifier.padding(universalPadding))
            if (imageUri.toString().isNotBlank() && imageUri != null) {
                Image(
                    painter = painter,
                    contentDescription = "Food Picture",
                    modifier = Modifier
                        .size(80.dp)
                        .clickable { launcher.launch("image/*") }
                        .clip(shape = RoundedCornerShape(universalPadding))
                )
            }
            else {
                Icon(
                    imageVector = Icons.Filled.Person,
                    contentDescription = "Default Profile Picture",
                    modifier = Modifier
                        .size(80.dp)
                        .clickable { launcher.launch("image/*") }
                )
            }

            //FOOD NAME
            TextField(
                value = foodName,
                onValueChange = { foodName = it },
                label = { Text(text = "Food Name")} ,
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier.fillMaxWidth()
            )
            //FOOD PRICE
            TextField(
                value = foodPrice,
                onValueChange = { foodPrice = it },
                label = { Text(text = "Food Price") },
                shape = RoundedCornerShape(20.dp),
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
                        if (foodName.isNotBlank() && foodPrice.isNotBlank() && imageUri.toString().isNotBlank())
                        {
                            onConfirm(foodName, foodPrice, imageUri?: Uri.EMPTY)
                            onDismiss()
                        }
                        else {
                            if (!isToastVisible) {
                                Toast.makeText(context,  "Fill in ALL Inputs.", Toast.LENGTH_SHORT).show()
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
