package com.example.a1.ui

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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController


@Composable
fun CustomizationScreen(navController: NavHostController){

    Box(
        modifier = Modifier
            .background(color = Color(0xFFFFFFFF))
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column {
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
        Row(Modifier.fillMaxWidth()) {

            BurgerImageBox(viewModel = viewModel, modifier = Modifier.weight(3f))
            Spacer(modifier = Modifier.width(6.dp))
            BurgerContent(modifier = Modifier.weight(1f))
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
                    .size(height= 80.dp, width = 170.dp)
                    .clickable { viewModel.onBunClick() }
            )
            if(viewModel.openDialogBun){
                CustomBunDialog(onDismiss = {viewModel.onBunDismissClick()},
                    onConfirm = {selection1 = it})
            }

            Image(
                painter = painterResource(selection2),
                contentDescription = "SELECT PATTY",
                modifier = Modifier
                    .padding(top = 10.dp, bottom = 10.dp, end = 16.dp)
                    .size(height= 80.dp, width = 170.dp)
                    .clickable {viewModel.onPattyClick()}
            )
            if(viewModel.openDialogPatty){
                CustomPattyDialog(onDismiss = {viewModel.onPattyDismissClick()},
                    onConfirm = {selection2 = it})
            }

            Image(
                painter = painterResource(selection3),
                contentDescription = "",
                modifier = Modifier
                    .padding(top = 10.dp, bottom = 10.dp, end = 16.dp)
                    .size(height= 80.dp, width = 170.dp)
                    .clickable {viewModel.onLettuceClick()},
            )
            if(viewModel.openDialogLettuce){
                CustomLettuceDialog(onDismiss = {viewModel.onLettuceDismissClick()},
                    onConfirm = {selection3 = it})
            }

            Image(
                painter = painterResource(selection4),
                contentDescription = "",
                modifier = Modifier
                    .padding(top = 10.dp, bottom = 10.dp, end = 16.dp)
                    .size(height= 80.dp, width = 170.dp)
                    .clickable {viewModel.onSauceClick()},
            )
            if(viewModel.openDialogSauce){
                CustomSauceDialog(onDismiss = {viewModel.onSauceDismissClick()},
                    onConfirm = {selection4 = it})
            }

            Image(
                painter = painterResource(selection5),
                contentDescription = "",
                modifier = Modifier
                    .padding(top = 10.dp, bottom = 10.dp, end = 16.dp)
                    .size(height= 80.dp, width = 170.dp)
                    .clickable {viewModel.onExtraClick()},
            )
            if(viewModel.openDialogExtra){
                CustomExtraDialog(onDismiss = {viewModel.onExtraDismissClick()},
                    onConfirm = {selection5 = it})
            }
            Image(
                painter = painterResource(R.drawable.bottombun),
                contentDescription = "",
                modifier = Modifier
                    .padding(top = 10.dp, bottom = 10.dp, end = 16.dp)
                    .size(height= 80.dp, width = 170.dp)
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
            .fillMaxHeight()
            .background(color = Color(0xFFF1CCA9))
            .border(1.5.dp, Color.Black)
            .size(width = 220.dp, height = 200.dp),
        contentAlignment = Alignment.TopStart

    ) {
        Column {
            Spacer(modifier = Modifier.height(30.dp))
            BurgerIngredient( R.drawable.bunicon, "Buns")
            SelectedIngredientDetails(newBunAdded, bunPrice)
            Spacer(modifier = Modifier.height(20.dp))
            BurgerIngredient( R.drawable.pattyicon, "Patty")
            SelectedIngredientDetails(newPattyAdded, PattyPrice)
            Spacer(modifier = Modifier.height(20.dp))
            BurgerIngredient( R.drawable.lettuceicon,"Lettuce")
            SelectedIngredientDetails(newLettuceAdded,LettucePrice)
            Spacer(modifier = Modifier.height(20.dp))
            BurgerIngredient( R.drawable.sauceicon,"Sauce")
            SelectedIngredientDetails(newSauceAdded, SaucePrice)
            Spacer(modifier = Modifier.height(20.dp))
            BurgerIngredient( R.drawable.extraicon,"Extra")
            SelectedIngredientDetails(newExtraAdded, ExtraPrice)
            Spacer(modifier = Modifier.height(30.dp))

            DisplayTotalPrice()
        }
    }
}

@Composable
fun BurgerIngredient(ingredientIcon: Int, ingredientText: String ){

    Column {
        Box(
            modifier = Modifier
                .height(25.dp)
                .width(110.dp)
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
@Composable
fun SelectedIngredientDetails(newStuffAdded: String, newPriceAdded: Double){
    Row {
        Text(textBlock(text = newStuffAdded),
            style = TextStyle(
                color = Color.Black,
                fontWeight = FontWeight.SemiBold),
            modifier = Modifier.padding(12.dp))

        Text(text = String.format(
            Locale.getDefault(),textBlock("%.2f"),newPriceAdded),
            style = TextStyle(
                color = Color.Black,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.End),
            modifier = Modifier.padding(12.dp))
    }
}

fun textBlock(text: String): String {

    val stringBuilder = StringBuilder()
    var charCount = 0
    for (char in text) {
        if (char == ' ') {
            stringBuilder.append('\n')
            charCount = 0 // Reset charCount for the next line
        } else {
            stringBuilder.append(char)
            charCount++
        }
    }
    return stringBuilder.toString()
}
@Composable
fun DisplayTotalPrice(){
    val viewModel : FoodMenuViewModel = viewModel()
    viewModel.CustomBurgertotalPrice()
    val displayPrice = viewModel.TotalPrice
    Text(text = String.format(
        Locale.getDefault(),"%.2f",displayPrice),
        style = TextStyle(
            color = Color.Black,
            fontWeight = FontWeight.SemiBold),
        textAlign = TextAlign.End,
        modifier = Modifier.padding(12.dp))
}



