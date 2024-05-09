package com.example.a1.ui

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.material3.Divider
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.Wrap


@Composable
fun MainPage(){
    Column {
        ImageSlider()
        LazyColumn (
            userScrollEnabled = true) {
            items(1) {
                MainPageBody()
                Footer()
            }
        }
    }
}
@Composable
fun ImageSlider()
{
    val isDarkTheme = isSystemInDarkTheme()

    val interval by remember { mutableStateOf(20000L) }
    val images = listOf(R.drawable.banner1, R.drawable.banner2, R.drawable.banner3)
    var currentIndex by remember { mutableStateOf(0) }

    var offsetX = remember { Animatable(initialValue = 1000f) }

    //greg here, this is a coroutine... yeah... ==================
    LaunchedEffect(currentIndex) {
        while (true) {
            delay(interval)
            currentIndex = (currentIndex + 1) % images.size
        }
    }
    LaunchedEffect(currentIndex) {
        offsetX.animateTo(
            targetValue = 0f,
            animationSpec = tween(durationMillis = 1000, easing = LinearOutSlowInEasing)
        )
    }

    Column(
        modifier = Modifier
            .height(300.dp)
            .fillMaxWidth()
            .background(if (isDarkTheme) Color.DarkGray else Color.LightGray)
    ) {
        //THE BANNER ==================
        Image(
            painter = painterResource(id = images[currentIndex]),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                //animation here
                .offset(x = offsetX.value.dp)
                .weight(1f)
        )

        //BUTTONS ==================
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
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
fun MainPageBody()
{
    val isDarkTheme = isSystemInDarkTheme()

    Column(Modifier.fillMaxWidth()) {
        //CUSTOM ITEMS
        Row(modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp)){
            Text(text = "Custom", color = Color(0xFFFDA6900), fontSize = 24.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(2.dp))
            Text(text = "Your", color =  if (isDarkTheme) Color.White else Color.Black, fontSize = 24.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(2.dp))
            Text(text = "Order", color = Color(0xFFFDA6900), fontSize = 24.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(2.dp))
        }
        Row (modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp)){
            Text(text = "Served the way you like it.", color =  if (isDarkTheme) Color.White else Color.Black, fontSize = 15.sp, modifier = Modifier.padding(2.dp))
        }
        CustomItem(R.drawable.burger, text = "Burger Maker")
        CustomItem(R.drawable.pizza, text = "Pizza Maker")

        Divider(thickness = 2.dp, modifier = Modifier.padding(4.dp))

        //INDIVIDUAL ITEMS
        Row(modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp)){
            Text(text = "Find", color = Color(0xFFFDA6900), fontSize = 22.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(2.dp))
            Text(text = "A", color =  if (isDarkTheme) Color.White else Color.Black, fontSize = 22.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(2.dp))
            Text(text = "Combo", color = Color(0xFFFDA6900), fontSize = 22.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(2.dp))
            Text(text = "...", color =  if (isDarkTheme) Color.White else Color.Black, fontSize = 22.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(2.dp))
        }
        FlowRow (maxItemsInEachRow = 2,
            horizontalArrangement = Arrangement.Center,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()) {
            FoodItem(R.drawable.fries, text = "Are Fries?")
            FoodItem(R.drawable.icecream, text = "Ice Cream")
            FoodItem(R.drawable.fries, text = "Are Fries?")
            FoodItem(R.drawable.icecream, text = "Ice Cream")
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
fun FoodItem(image : Int, text: String, onClickAction : () -> Unit = {}) {
    val isDarkTheme = isSystemInDarkTheme()

    Card(
        shape = RoundedCornerShape(18.dp),
        border = BorderStroke(2.dp, Color.Black),
        modifier = Modifier
            .padding(8.dp)
            .clickable { onClickAction() }
            .fillMaxWidth(0.45f)
    ) {
        Column {
            Image(
                painter = painterResource(image), contentDescription = text,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(115.dp),
                contentScale = ContentScale.Crop
            )
            Text(
                text = text,
                color = (if (isDarkTheme) Color.White else Color.Black),
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

