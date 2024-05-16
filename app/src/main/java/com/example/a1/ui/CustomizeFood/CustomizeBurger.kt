package com.example.a1.ui.CustomizeFood

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.example.a1.R
import com.example.test.CustomBunDialog
import com.example.test.CustomExtraDialog
import com.example.test.CustomLettuceDialog
import com.example.test.CustomPattyDialog
import com.example.test.CustomSauceDialog
import com.example.test.FoodMenuViewModel
import java.util.Locale
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
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text

@Composable
fun CustomizationScreen(navController: NavHostController){
//Screen settings
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp

    val isDarkTheme = isSystemInDarkTheme()


    Box(
        modifier = Modifier
            .background(color = Color(0xFFFFFFFF))
            .fillMaxSize()
            .padding(top = 5.dp, start = 16.dp, end = 16.dp, bottom = 8.dp)
    ) {
        Column {
            Row {
                IconButton(
                    colors = IconButtonDefaults.iconButtonColors(Color(0xFFFF9D7E)),
                    onClick = { navController.popBackStack() },
                    modifier = Modifier
                        .border(
                            3.5.dp,
                            color = if (isDarkTheme) Color.White else Color(0xFF975743),
                            shape = CircleShape
                        )
                        .width(300.dp)
                        .height(40.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            tint = if (isDarkTheme) Color.White else Color.Black,
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back Button",
                            modifier = Modifier
                                .fillMaxHeight()
                                .padding(8.dp)
                        )
                        Spacer(modifier = Modifier.weight(0.1f))
                        Text("Cancel Order/Back to Main Page",color = if (isDarkTheme) Color.White else Color.Black,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(8.dp)
                        )
                        Spacer(modifier = Modifier.weight(0.1f))
                    }
                }
            }
            CustomizeMenu()
        }
    }
}
@Composable
fun CustomizeMenu() {
    val viewModel: FoodMenuViewModel = viewModel()

    Box(
        modifier = Modifier
            .background(color = Color(0xFFFFEEB1))
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column {
            Row(Modifier.fillMaxWidth()) {

                BurgerImageBox(viewModel = viewModel, modifier = Modifier.weight(3f))
                Spacer(modifier = Modifier.width(6.dp))
                BurgerContent(modifier = Modifier.weight(1f))
            }
                Button(onClick = { /*TODO*/ },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF44FF8F) // Background color
                    ),
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .height(40.dp)
                        .width(400.dp)
                    ) {
                    Text(text = "Add To Cart")
                }
        }

    }
}

@Composable
fun BurgerImageBox(viewModel: FoodMenuViewModel, modifier: Modifier = Modifier){
    var selection1 = viewModel.addBun.image
    var selection2 = viewModel.addPatty.image
    var selection3 = viewModel.addLettuce.image
    var selection4 = viewModel.addSauce.image
    var selection5 = viewModel.addExtra.image


    Box(
        modifier = Modifier
            .background(color = Color(0xFFFFFFFF))
            .border(1.5.dp, Color.Black)


    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.Start
        ) {

            Image(
                painter = painterResource(selection1),
                contentDescription = "SELECT BUN",
                modifier = Modifier
                    .padding(top = 10.dp, bottom = 10.dp, end = 16.dp)
                    .size(height = 80.dp, width = 170.dp)
                    .clickable { viewModel.onBunClick() }
            )
            if(viewModel.openDialogBun){
                CustomBunDialog( onDismiss = {viewModel.onBunDismissClick()},
                    onConfirm = {BurgerImage ->
                        selection1 = BurgerImage
                        viewModel.onBunDismissClick()
                    })
            }

            Image(
                painter = painterResource(selection2),
                contentDescription = "SELECT PATTY",
                modifier = Modifier
                    .padding(top = 10.dp, bottom = 10.dp, end = 16.dp)
                    .size(height = 80.dp, width = 170.dp)
                    .clickable { viewModel.onPattyClick() }
            )
            if(viewModel.openDialogPatty){
                CustomPattyDialog(onDismiss = {viewModel.onPattyDismissClick()},
                    onConfirm = {BurgerImage ->
                        selection2 = BurgerImage
                        viewModel.onBunDismissClick()})
            }

            Image(
                painter = painterResource(selection3),
                contentDescription = "",
                modifier = Modifier
                    .padding(top = 10.dp, bottom = 10.dp, end = 16.dp)
                    .size(height = 80.dp, width = 170.dp)
                    .clickable { viewModel.onLettuceClick() },
            )
            if(viewModel.openDialogLettuce){
                CustomLettuceDialog(onDismiss = {viewModel.onLettuceDismissClick()},
                    onConfirm = {BurgerImage ->
                        selection3 = BurgerImage
                        viewModel.onBunDismissClick()})
            }

            Image(
                painter = painterResource(selection4),
                contentDescription = "",
                modifier = Modifier
                    .padding(top = 10.dp, bottom = 10.dp, end = 16.dp)
                    .size(height = 80.dp, width = 170.dp)
                    .clickable { viewModel.onSauceClick() },
            )
            if(viewModel.openDialogSauce){
                CustomSauceDialog(onDismiss = {viewModel.onSauceDismissClick()},
                    onConfirm = {BurgerImage ->
                        selection4 = BurgerImage
                        viewModel.onBunDismissClick()})
            }

            Image(
                painter = painterResource(selection5),
                contentDescription = "",
                modifier = Modifier
                    .padding(top = 10.dp, bottom = 10.dp, end = 16.dp)
                    .size(height = 80.dp, width = 170.dp)
                    .clickable { viewModel.onExtraClick() },
            )
            if(viewModel.openDialogExtra){
                CustomExtraDialog(onDismiss = {viewModel.onExtraDismissClick()},
                    onConfirm = {BurgerImage ->
                        selection5 = BurgerImage
                        viewModel.onBunDismissClick()})
            }
            Image(
                painter = painterResource(R.drawable.bottombun),
                contentDescription = "",
                modifier = Modifier
                    .padding(top = 10.dp, bottom = 10.dp, end = 16.dp)
                    .size(height = 80.dp, width = 170.dp)
            )

        }
    }
}



@Composable
fun BurgerContent(modifier: Modifier = Modifier){
    val viewModel: FoodMenuViewModel = viewModel()
    val newBunAdded = viewModel.addBun.name
    val newPattyAdded = viewModel.addPatty.name
    val newLettuceAdded = viewModel.addLettuce.name
    val newSauceAdded = viewModel.addSauce.name
    val newExtraAdded = viewModel.addExtra.name
    val bunPrice = viewModel.addBun.price
    val PattyPrice = viewModel.addPatty.price
    val LettucePrice = viewModel.addLettuce.price
    val SaucePrice = viewModel.addSauce.price
    val ExtraPrice = viewModel.addExtra.price

    Box(
        modifier = Modifier
            .background(color = Color(0xFFF1CCA9))
            .border(1.5.dp, Color.Black)
            .size(width = 220.dp, height = 630.dp),
        contentAlignment = Alignment.TopStart

    ) {
        Column {
            Spacer(modifier = Modifier.height(30.dp))
            BurgerIngredient( R.drawable.bunicon, "Buns")
            SelectedIngredientDetails(newBunAdded, bunPrice)
            Spacer(modifier = Modifier.height(10.dp))
            BurgerIngredient( R.drawable.pattyicon, "Patty")
            SelectedIngredientDetails(newPattyAdded, PattyPrice)
            Spacer(modifier = Modifier.height(10.dp))
            BurgerIngredient( R.drawable.lettuceicon,"Lettuce")
            SelectedIngredientDetails(newLettuceAdded,LettucePrice)
            Spacer(modifier = Modifier.height(10.dp))
            BurgerIngredient( R.drawable.sauceicon,"Sauce")
            SelectedIngredientDetails(newSauceAdded, SaucePrice)
            Spacer(modifier = Modifier.height(10.dp))
            BurgerIngredient( R.drawable.extraicon,"Extra")
            SelectedIngredientDetails(newExtraAdded, ExtraPrice)
            Spacer(modifier = Modifier.height(30.dp))

            DisplayTotalPrice()
        }
    }
}
//==============================MAKING THE ICON FOR EACH BURGER CATEGORY IN THE RIGHT SIDE BOX========================
@Composable
fun BurgerIngredient(ingredientIcon: Int, ingredientText: String ){

    Column {
        Box(
            modifier = Modifier
                .height(25.dp)
                .width(100.dp)
                .background(Color.White, shape = RoundedCornerShape(16.dp)),
            contentAlignment = Alignment.CenterStart
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = ingredientIcon),
                    contentDescription = "",
                    modifier = Modifier
                        .padding(start = 10.dp, end = 10.dp)
                        .size(width = 25.dp, height = 25.dp)
                )
                Text(
                    text = ingredientText,
                    style = TextStyle(
                        color = Color.Black,
                        fontWeight = FontWeight.Bold
                    ),
                    textAlign = TextAlign.Start
                )
            }
        }

    }
}
//========================================THE RIGHT SIDE BOX'S CONTENT================================
@Composable
fun SelectedIngredientDetails(newStuffAdded: String, newPriceAdded: Double){
    Column(modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.End,){
        Text(text = newStuffAdded,
            style = TextStyle(
                color = Color.Black,
                fontWeight = FontWeight.SemiBold),
            modifier = Modifier.padding(start = 4.dp, end = 8.dp, top = 4.dp))

        Text(text = String.format(
            Locale.getDefault(),("RM %.2f"),newPriceAdded),
            style = TextStyle(
                color = Color.Black,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.End),
            modifier = Modifier.padding(12.dp))
    }
}
//=====================DISPLAY THE TOTAL PRICE OF THE BURGER AT THE BOTTOM RIGHT=============================
@Composable
fun DisplayTotalPrice(){
    val viewModel :FoodMenuViewModel = viewModel()
    viewModel.CustomBurgertotalPrice()
    val displayPrice = viewModel.TotalPrice
    Row( modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically) {
        Text(text = String.format(
            Locale.getDefault(),"RM %.2f",displayPrice),
            style = TextStyle(
                color = Color.Black,
                fontWeight = FontWeight.SemiBold,
                fontSize = 24.sp),
            textAlign = TextAlign.End,
            modifier = Modifier.padding(12.dp))
    }
}


