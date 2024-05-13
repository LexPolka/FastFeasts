package com.example.a1.ui

import android.widget.Space
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.a1.Footer
import com.example.a1.R
import kotlinx.coroutines.delay
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.material.icons.outlined.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.a1.FastFeastsScreen
import com.example.a1.data.profiledata.GlobalViewModel
import kotlinx.coroutines.launch

@Composable
fun MainPage(viewModel : GlobalViewModel, navController: NavHostController){
    val isDarkTheme = isSystemInDarkTheme()

    //Screen settings
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val viewCartWidth = screenWidth * 45/100

    Box {
        LazyColumn (
            userScrollEnabled = true) {
            items(1) {
                ImageSlider()
                Divider(thickness = 2.dp, color = if (isDarkTheme) Color.White else Color.Black)
                MainPageBody(viewModel, navController)
                Divider(thickness = 2.dp, color = if (isDarkTheme) Color.White else Color.Black)
                Footer()
            }
        }

        // VIEW CART BUTTON
        IconButton(
            onClick = { navController.navigate(FastFeastsScreen.Cart.name) },
            modifier = Modifier
                .padding(20.dp)
                .width(viewCartWidth)
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
                Spacer(Modifier.weight(0.3f))
                Icon(
                    painter = painterResource(R.drawable.cart),
                    contentDescription = "View Cart",
                    tint = Color.White,
                )
                Spacer(Modifier.weight(0.3f))
                Text(text = "View Cart", color =  if (isDarkTheme) Color.White else Color.Black, fontSize = 18.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(2.dp))
                Spacer(Modifier.weight(0.3f))
            }

        }
    }

}

@Composable
fun ImageSlider()
{
    val isDarkTheme = isSystemInDarkTheme()

    //Screen settings
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val bannerHeight = screenWidth * 70/100

    val interval by remember { mutableStateOf(5000L) }
    val images = listOf(R.drawable.banner1, R.drawable.banner2, R.drawable.banner3)
    var currentIndex by remember { mutableStateOf(0) }

    //greg here, this is a coroutine... yeah... ==================
    LaunchedEffect(currentIndex) {
        while (true) {
            delay(interval)
            currentIndex = (currentIndex + 1) % images.size
        }
    }

    //THE BANNER ==================
    Box (modifier = Modifier
        .fillMaxWidth()
        .height(bannerHeight)
        .background(if (isDarkTheme) Color.DarkGray else Color.LightGray))
    {
        Image(
            painter = painterResource(id = images[currentIndex]),
            contentDescription = "Banner",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .matchParentSize()
        )
        IconButton(
            onClick = { if (currentIndex == (images.size - 1)) currentIndex = 0 else currentIndex += 1},
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .background(Color.LightGray.copy(alpha = 0.2f), shape = CircleShape)
                .fillMaxHeight(0.9f)
                .padding(1.dp)
        ) {
            Icon(
                imageVector = Icons.Outlined.KeyboardArrowRight,
                contentDescription = "Go Right",
                tint = Color.White,
                modifier = Modifier.matchParentSize()
            )
        }
        IconButton(
            onClick = { if (currentIndex == 0) currentIndex = (images.size - 1) else currentIndex -= 1 },
            modifier = Modifier
                .align(Alignment.CenterStart)
                .background(Color.LightGray.copy(alpha = 0.2f), shape = CircleShape)
                .fillMaxHeight(0.9f)
                .padding(1.dp)
        ) {
            Icon(
                imageVector = Icons.Outlined.KeyboardArrowLeft,
                contentDescription = "Go Left",
                tint = Color.White,
                modifier = Modifier.matchParentSize()
            )
        }

        //BUTTONS ==================
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .align(Alignment.BottomCenter)
        ) {
            // for every image we use in the images array, a button is made for it...
            // THIS TOOK FOREVER TO FIGURE OUT RAAAAAAAAAAAAAAAAAAAAAAAAAAAA I HATE MYSELF I HATE MYSELF I HATE THIS SUBJECT
            // images, an array .forEachIndexed (for each object in array) index is a new var, will make a Surface (the button).
            images.forEachIndexed { index, _ ->
                Surface(
                    color = (if (index == currentIndex) Color.Black else Color.LightGray),
                    shape = CircleShape,
                    border = BorderStroke(1.dp, if (isDarkTheme) Color.White else Color.DarkGray),
                    modifier = Modifier
                        .size(20.dp)
                        .padding(4.dp)
                        .clickable { currentIndex = index }
                ) {}
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun MainPageBody(viewModel : GlobalViewModel, navController: NavHostController)
{
    val isDarkTheme = isSystemInDarkTheme()

    Column(Modifier.fillMaxWidth()) {
        //CUSTOM ITEMS
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)){
            Text(text = "Custom", color = Color(0xFFFDA6900), fontSize = 24.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(2.dp))
            Text(text = "Your", color =  if (isDarkTheme) Color.White else Color.Black, fontSize = 24.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(2.dp))
            Text(text = "Order", color = Color(0xFFFDA6900), fontSize = 24.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(2.dp))
        }
        Row (modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)){
            Text(text = "Served the way you like it.", color =  if (isDarkTheme) Color.White else Color.Black, fontSize = 15.sp, modifier = Modifier.padding(2.dp))
        }
        CustomItem(R.drawable.burger, text = "Burger Maker")
        CustomItem(R.drawable.pizza, text = "Pizza Maker")

        Divider(thickness = 2.dp, modifier = Modifier.padding(4.dp))

        //INDIVIDUAL ITEMS
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)){
            Text(text = "Find", color = Color(0xFFFDA6900), fontSize = 22.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(2.dp))
            Text(text = "A", color =  if (isDarkTheme) Color.White else Color.Black, fontSize = 22.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(2.dp))
            Text(text = "Combo", color = Color(0xFFFDA6900), fontSize = 22.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(2.dp))
            Text(text = "...", color =  if (isDarkTheme) Color.White else Color.Black, fontSize = 22.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(2.dp))
        }
        FlowRow (maxItemsInEachRow = 2,
            horizontalArrangement = Arrangement.Center,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()) {
            IndividualFoodItem(R.drawable.fries, "Are Fries?", "5.90", navController, viewModel)
            IndividualFoodItem(R.drawable.icecream, "Ice Cream", "2.50", navController, viewModel)
        }
    }
}

@Composable
fun CustomItem(image : Int, text: String, onClickAction : () -> Unit = {}) {
    val isDarkTheme = isSystemInDarkTheme()

    Card(
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(3.dp, Color.Black),
        modifier = Modifier
            .padding(8.dp)
            .clickable { onClickAction() }
    ){
        Column {
            Image(
                painter = painterResource(image), contentDescription = text,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp),
                contentScale = ContentScale.Crop
            )
            Text(
                text = text,
                color = (if(isDarkTheme) Color.White else Color.Black),
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

@Composable
fun IndividualFoodItem(image : Int, name: String, price : String, navController: NavHostController, viewModel : GlobalViewModel) {
    val isDarkTheme = isSystemInDarkTheme()

    Card(
        shape = RoundedCornerShape(18.dp),
        border = BorderStroke(2.dp, Color.Black),
        modifier = Modifier
            .padding(8.dp)
            .clickable {
                viewModel.setDisplayIndividualFood(image, name, price)
                navController.navigate(FastFeastsScreen.IndividualFood.name)
            }
            .fillMaxWidth(0.45f)
    ) {
        Column {
            Image(
                painter = painterResource(image), contentDescription = name,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(115.dp),
                contentScale = ContentScale.Crop
            )
            Text(
                text = name,
                color = (if (isDarkTheme) Color.White else Color.Black),
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

