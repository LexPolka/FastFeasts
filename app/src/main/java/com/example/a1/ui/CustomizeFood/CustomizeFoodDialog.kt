package com.example.test

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.ButtonDefaults
import androidx.wear.compose.material.Text
import com.example.a1.R
import com.example.a1.data.AppViewModelProvider
import com.example.a1.data.CustomizeFood.StockViewModel
import java.util.Locale


//==============================================FUNCTIONS FOR DECORATING THE DIALOG SELECTION==========================================
@Composable
fun BunSelection(Id: Int, viewModel: FoodMenuViewModel,Name: String, Price: Double, Image1: Int, Image2: Int,onDismiss: () -> Unit, onConfirm: (Int) -> Unit ){

             Box(modifier = Modifier
                 .background(color = Color(0xFFD9D9D9))
                 .border(1.5.dp, Color.Black)
                 .size(height = 150.dp, width = 150.dp),
                 contentAlignment = Alignment.TopCenter
             ){
               Column (horizontalAlignment = Alignment.CenterHorizontally){
                    Image(
                      painter = painterResource(id = Image1),
                      contentDescription = Name,
                       modifier = Modifier
                           .border(1.5.dp, Color.Black)
                           .size(height = 70.dp, width = 150.dp)
                           .background(color = Color.White))

                Text(text = String.format(
                 Locale.getDefault(),("%s\nRM %.2f"),Name,Price), style = TextStyle(
                   color = Color.Black,
                   fontWeight = FontWeight.Bold),
                   textAlign = TextAlign.Center)
                   Spacer(modifier = Modifier.padding(4.dp))
                Button(onClick = {
                   val addedBuns = viewModel.Buns(
                    name = Name,
                    price = Price,
                    image = Image2)
                    viewModel.addedNewBun(newBun = addedBuns)
                    onConfirm(Image2)
                    viewModel.idBun = Id
                    onDismiss() },
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color( 0xFFFF9D7E)),
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .height(30.dp)
                        .width(80.dp)
                )
                 {
                    Text(text = "ORDER",style = TextStyle(
                        color = Color.White,
                        background = Color( 0xFFFF9D7E),
                        fontWeight = FontWeight.Bold))
                }
            }
        }
}

@Composable
fun PattySelection(Id: Int, viewModel: FoodMenuViewModel,Name: String, Price: Double, Image1: Int, Image2: Int,onDismiss: () -> Unit, onConfirm: (Int) -> Unit ){
    Box(modifier = Modifier
        .background(color = Color(0xFFD9D9D9))
        .border(1.5.dp, Color.Black)
        .size(height = 150.dp, width = 150.dp),
        contentAlignment = Alignment.TopCenter
    ){
        Column (horizontalAlignment = Alignment.CenterHorizontally){
            Image(
                painter = painterResource(id = Image1),
                contentDescription = Name,
                modifier = Modifier
                    .border(1.5.dp, Color.Black)
                    .size(height = 70.dp, width = 150.dp)
                    .background(color = Color.White)
            )

            Text(text = String.format(
                Locale.getDefault(),("%s\nRM %.2f"),Name,Price), style = TextStyle(
                color = Color.Black,
                fontWeight = FontWeight.Bold),
                textAlign = TextAlign.Center)
            Spacer(modifier = Modifier.padding(4.dp))
            Button(onClick = {
                val addedPatty = viewModel.Patty(
                    name = Name,
                    price = Price,
                    image = Image2
                )
                viewModel.addedNewPatty(newPatty = addedPatty)
                onConfirm(Image2)
                viewModel.idPatty = Id
                onDismiss() },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color( 0xFFFF9D7E)),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .height(30.dp)
                    .width(80.dp)
            )
            {
                Text(text = "ORDER",style = TextStyle(
                    color = Color.White,
                    background = Color( 0xFFFF9D7E),
                    fontWeight = FontWeight.Bold))
            }
        }
    }
}
@Composable
fun LettuceSelection(Id: Int, viewModel: FoodMenuViewModel,Name: String, Price: Double, Image1: Int, Image2: Int,onDismiss: () -> Unit, onConfirm: (Int) -> Unit ){
    Box(modifier = Modifier
        .background(color = Color(0xFFD9D9D9))
        .border(1.5.dp, Color.Black)
        .size(height = 150.dp, width = 150.dp),
        contentAlignment = Alignment.TopCenter
    ){
        Column (horizontalAlignment = Alignment.CenterHorizontally){
            Image(
                painter = painterResource(id = Image1),
                contentDescription = Name,
                modifier = Modifier
                    .border(1.5.dp, Color.Black)
                    .size(height = 70.dp, width = 150.dp)
                    .background(color = Color.White)
            )

            Text(text = String.format(
                Locale.getDefault(),("%s\nRM %.2f"),Name,Price), style = TextStyle(
                color = Color.Black,
                fontWeight = FontWeight.Bold),
                textAlign = TextAlign.Center)
            Spacer(modifier = Modifier.padding(4.dp))
            Button(onClick = {
                val addedLettuce = viewModel.Lettuce(
                    name = Name,
                    price = Price,
                    image = Image2
                )
                viewModel.addedNewLettuce(newLettuce = addedLettuce)
                onConfirm(Image2)
                viewModel.idLettuce = Id
                onDismiss() },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color( 0xFFFF9D7E)),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .height(30.dp)
                    .width(80.dp)
            )
            {
                Text(text = "ORDER",style = TextStyle(
                    color = Color.White,
                    background = Color( 0xFFFF9D7E),
                    fontWeight = FontWeight.Bold))
            }
        }
    }
}
@Composable
fun SauceSelection(Id: Int,viewModel: FoodMenuViewModel,Name: String, Price: Double, Image1: Int, Image2: Int,onDismiss: () -> Unit, onConfirm: (Int) -> Unit ){
    Box(modifier = Modifier
        .background(color = Color(0xFFD9D9D9))
        .border(1.5.dp, Color.Black)
        .size(height = 150.dp, width = 150.dp),
        contentAlignment = Alignment.TopCenter
    ){
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(id = Image1),
                contentDescription = Name,
                modifier = Modifier
                    .border(1.5.dp, Color.Black)
                    .size(height = 70.dp, width = 150.dp)
                    .background(color = Color.White)
            )

            Text(text = String.format(
                Locale.getDefault(),("%s\nRM %.2f"),Name,Price), style = TextStyle(
                color = Color.Black,
                fontWeight = FontWeight.Bold),
                textAlign = TextAlign.Center)
            Spacer(modifier = Modifier.padding(4.dp))
            Button(onClick = {
                val addedSauce = viewModel.Sauce(
                    name = Name,
                    price = Price,
                    image = Image2
                )
                viewModel.addedNewSauce(newSauce = addedSauce)
                onConfirm(Image2)
                viewModel.idSauce = Id
                onDismiss()},
                colors = ButtonDefaults.buttonColors(backgroundColor = Color( 0xFFFF9D7E)),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .height(30.dp)
                    .width(80.dp)
            )
            {
                Text(text = "ORDER",style = TextStyle(
                    color = Color.White,
                    background = Color( 0xFFFF9D7E),
                    fontWeight = FontWeight.Bold))
            }
        }
    }
}
@Composable
fun ExtraSelection(Id: Int, viewModel: FoodMenuViewModel,Name: String, Price: Double, Image1: Int, Image2: Int,onDismiss: () -> Unit, onConfirm: (Int) -> Unit){
    Box(modifier = Modifier
        .background(color = Color(0xFFD9D9D9))
        .border(1.5.dp, Color.Black)
        .size(height = 150.dp, width = 150.dp),
        contentAlignment = Alignment.TopCenter
    ){
        Column (horizontalAlignment = Alignment.CenterHorizontally){

            Image(
                painter = painterResource(id = Image1),
                contentDescription = Name,
                modifier = Modifier
                    .border(1.5.dp, Color.Black)
                    .size(height = 70.dp, width = 150.dp)
                    .background(color = Color.White)
            )

            Text(text = String.format(
                Locale.getDefault(),("%s\nRM %.2f"),Name,Price), style = TextStyle(
                color = Color.Black,
                fontWeight = FontWeight.Bold),
                textAlign = TextAlign.Center)
            Spacer(modifier = Modifier.padding(4.dp))
            Button(onClick = {

                val addedExtra = viewModel.Extra(
                    name = Name,
                    price = Price,
                    image = Image2
                )
                viewModel.addedNewExtra(newExtra = addedExtra)
                onConfirm(Image2)
                viewModel.idExtra = Id
                onDismiss()
                },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color( 0xFFFF9D7E)),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .height(30.dp)
                    .width(80.dp)
            )
            {
                Text(text = "ORDER",style = TextStyle(
                    color = Color.White,
                    background = Color( 0xFFFF9D7E),
                    fontWeight = FontWeight.Bold))
            }
        }
    }
}

//===========================================ICONS FOR THE INGREDIENT CATEGORY=============================================
@Composable
fun BurgerCategory(ingredientIcon: Int, ingredientText: String ){

    Column {
        Box(
            modifier = Modifier
                .height(30.dp)
                .width(110.dp)
                .background(Color(0xFFFF9E0C), shape = RoundedCornerShape(16.dp)),
            contentAlignment = Alignment.Center,
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = ingredientIcon),
                    contentDescription = "",
                    modifier = Modifier
                        .padding(start = 10.dp, end = 10.dp)
                        .size(width = 40.dp, height = 30.dp)
                        .clip(RoundedCornerShape(10.dp))
                )
                androidx.compose.material3.Text(
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



//============================================DIALOG FOR THE INGREDIENT SELECTION==========================================
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CustomBunDialog(
    onDismiss: () -> Unit,
    onConfirm: (Int) -> Unit
){

    Dialog(
        onDismissRequest = { onDismiss() },
        properties = DialogProperties(usePlatformDefaultWidth = false)
    )
    {
        androidx.compose.material3.Card(
            shape = RoundedCornerShape(15.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp)
                .size(height = 650.dp, width = 10.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFF1CCA9))
        )
        { BurgerCategory( R.drawable.bunicon, "Buns")
            FlowRow(
                horizontalArrangement = Arrangement.Center,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp)
                    .padding(top = 20.dp)
            )
            {

                BunSelection(1, viewModel(),"Regular Bun", 1.50, R.drawable.burger_buns,R.drawable.burger_buns, onDismiss, onConfirm)
                Spacer(modifier = Modifier.padding(5.dp))
                BunSelection(2, viewModel(),"Pretzel Bun", 1.80, R.drawable.pretzel_buns, R.drawable.pretzel_buns, onDismiss, onConfirm)
                Spacer(modifier = Modifier.padding(5.dp))
                BunSelection(3, viewModel(),"Wheat Bun", 1.80, R.drawable.wheat_bun,R.drawable.wheat_bun, onDismiss, onConfirm)
                Spacer(modifier = Modifier.padding(5.dp))
                BunSelection(15, viewModel(),"[EMPTY SLOT]", 0.00, R.drawable.cancelorder,R.drawable.emptyslot, onDismiss, onConfirm)

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
    Dialog(
        onDismissRequest = { onDismiss() },
        properties = DialogProperties(usePlatformDefaultWidth = false)
    )
    {
        androidx.compose.material3.Card(
            shape = RoundedCornerShape(15.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp)
                .size(height = 650.dp, width = 10.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFF1CCA9))
        )
        {   BurgerCategory( R.drawable.pattyicon, "Patty")
            FlowRow(
                horizontalArrangement = Arrangement.Center,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp)
                    .padding(top = 20.dp)
            )
            {
                PattySelection(4, viewModel(),"Chicken Patty", 3.00, R.drawable.patty1,R.drawable.patty1,onDismiss, onConfirm)
                Spacer(modifier = Modifier.padding(5.dp))
                PattySelection(5, viewModel(),"Beef Patty", 4.20, R.drawable.beefpatty,R.drawable.beefpatty,onDismiss, onConfirm)
                PattySelection(6, viewModel(),"Bacon Patty", 4.00, R.drawable.baconpatty,R.drawable.baconpatty,onDismiss, onConfirm)
                Spacer(modifier = Modifier.padding(5.dp))
                PattySelection(15, viewModel(),"[EMPTY SLOT]", 0.00, R.drawable.cancelorder,R.drawable.emptyslot,onDismiss, onConfirm)
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
    Dialog(
        onDismissRequest = { onDismiss() },
        properties = DialogProperties(usePlatformDefaultWidth = false)
    )
    {
        androidx.compose.material3.Card(
            shape = RoundedCornerShape(15.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp)
                .size(height = 650.dp, width = 10.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFF1CCA9))
        )
        {   BurgerCategory( R.drawable.lettuceicon, "Lettuce")
            FlowRow(
                horizontalArrangement = Arrangement.Center,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp)
                    .padding(top = 20.dp)
            )
            {
                LettuceSelection(7, viewModel(),"Iceberg Lettuce", 1.00, R.drawable.iceberg_lettuceinmenu, R.drawable.iceberg_lettuce,onDismiss, onConfirm)
                Spacer(modifier = Modifier.padding(5.dp))
                LettuceSelection(8, viewModel(),"Romaine Lettuce", 1.00, R.drawable.romaine_lettuceinmenu, R.drawable.iceberg_lettuce,onDismiss, onConfirm)
                Spacer(modifier = Modifier.padding(5.dp))
                LettuceSelection(15, viewModel(),"[EMPTY SLOT]", 0.00, R.drawable.cancelorder, R.drawable.emptyslot,onDismiss, onConfirm)
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
    Dialog(
        onDismissRequest = { onDismiss() },
        properties = DialogProperties(usePlatformDefaultWidth = false)
    )
    {
        androidx.compose.material3.Card(
            shape = RoundedCornerShape(15.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp)
                .size(height = 650.dp, width = 10.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFF1CCA9))
        )
        {   BurgerCategory( R.drawable.sauceicon, "Sauce")
            FlowRow(
                horizontalArrangement = Arrangement.Center,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp)
                    .padding(top = 20.dp)
            )
            {
                SauceSelection(9, viewModel(),"Tomato Sauce", 0.20, R.drawable.tomatosauceinmenu, R.drawable.tomato_sauce, onDismiss, onConfirm)
                Spacer(modifier = Modifier.padding(5.dp))
                SauceSelection(10, viewModel(),"Chilli Sauce", 0.20, R.drawable.chillisauceinmenu, R.drawable.chilli_sauce, onDismiss, onConfirm)
                Spacer(modifier = Modifier.padding(5.dp))
                SauceSelection(11, viewModel(),"BBQ Sauce", 0.40, R.drawable.bbqsauceinmenu, R.drawable.bbq_sauce, onDismiss, onConfirm)
                Spacer(modifier = Modifier.padding(5.dp))
                SauceSelection(12, viewModel(),"Mustard Sauce", 0.20, R.drawable.mustardsauceinmenu,R.drawable.mustard_sauce, onDismiss, onConfirm)
                Spacer(modifier = Modifier.padding(5.dp))
                SauceSelection(15, viewModel(),"[EMPTY SLOT]", 0.00, R.drawable.cancelorder,R.drawable.emptyslot, onDismiss, onConfirm)

            }
        }

    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CustomExtraDialog(
    onDismiss: () -> Unit,
    onConfirm: (Int) -> Unit)
{
    Dialog(
        onDismissRequest = { onDismiss() },
        properties = DialogProperties(usePlatformDefaultWidth = false)
    )
    {
        androidx.compose.material3.Card(
            shape = RoundedCornerShape(15.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp)
                .size(height = 650.dp, width = 10.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFF1CCA9))
        )
        {   BurgerCategory( R.drawable.extraicon, "Extra")
            FlowRow(
                horizontalArrangement = Arrangement.Center,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp)
                    .padding(top = 20.dp)
            )
            {
                ExtraSelection(13,viewModel(), "Egg", 1.00 , R.drawable.egg ,R.drawable.egg, onDismiss, onConfirm )
                Spacer(modifier = Modifier.padding(5.dp))
                ExtraSelection(14,viewModel(), "Cheese", 1.00 , R.drawable.cheese ,R.drawable.cheese , onDismiss, onConfirm)
                Spacer(modifier = Modifier.padding(5.dp))
                ExtraSelection(15, viewModel(),"[EMPTY SLOT]", 0.00, R.drawable.cancelorder,R.drawable.emptyslot,onDismiss, onConfirm)
            }
        }

    }
}