package com.example.a1.ui.staffUI

import android.media.Image
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.a1.data.staffdata.OrderEntity
import com.example.a1.data.staffdata.StaffViewModel
import com.example.a1.ui.fastFeast.BackButton
import com.example.a1.ui.fastFeast.imageBitmapFromBytes
import androidx.compose.runtime.setValue
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

@Composable
fun StaffOrders(viewModel: StaffViewModel, navController: NavHostController){
    val orderList by viewModel.orderList.collectAsState()

    //SORT BY ID (this is so cool omg)
    val groupedOrders = orderList.groupBy { it.orderID }

    Column {
        BackButton(navController = navController)

        Text(
            text = "Pending Orders",
            fontSize = 20.sp,
            fontWeight = Bold,
            modifier = Modifier.padding(8.dp) // Add bottom padding between title and items
        )

        LazyColumn {
            groupedOrders.forEach { (orderID , orders) ->
                item {
                    OrderGroup(orderID, orders, viewModel)
                }
            }
        }
    }

}

@Composable
fun OrderGroup(orderID : String, orders: List<OrderEntity>, viewModel : StaffViewModel) {
    var showDialog by remember { mutableStateOf(false) }

    Column (
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .border(3.dp, color = Color.LightGray, shape = RoundedCornerShape(20.dp))
            .background(
                color = Color.DarkGray,
                shape = RoundedCornerShape(20.dp)
            )
    ){
        Row(
            modifier = Modifier
                .padding(8.dp)
                .height(40.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = "Order ID: $orderID",
                fontSize = 20.sp,
                fontWeight = Bold,
                modifier = Modifier.weight(1f)
            )

            IconButton(
                onClick = { showDialog = true },
                modifier = Modifier
                    .size(135.dp)
                    .padding(8.dp)
                    .background(Color.White, shape = RoundedCornerShape(20.dp))
                    .border(3.5.dp, Color(0xFFFF9D7E), shape = RoundedCornerShape(20.dp)),
                colors = IconButtonDefaults.iconButtonColors(contentColor = Color.Black)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = "Complete Order",
                        tint = Color.Green
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = "Completed", fontWeight = Bold,)
                }
            }
        }


        orders.forEach { order ->
            OrderRow(order = order)
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

                Text("Confirm Order Completion", fontSize = 20.sp, fontWeight = Bold, modifier = Modifier.padding(top = 4.dp))
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
                            viewModel.deleteOrder(orderID)
                            showDialog = false
                        },
                        modifier = Modifier
                            .padding(8.dp)
                            .border(3.5.dp, Color.White, shape = CircleShape)
                            .width(100.dp)
                    ) {
                        Text("Completed", color = Color.White)
                    }
                }
            }
        }
    }
}

@Composable
fun OrderRow(order: OrderEntity) {
    val imageFromByteToBitmap = imageBitmapFromBytes(order.image)

    Row (modifier = Modifier
        .fillMaxWidth()
        .padding(bottom = 5.dp)) {
        Column {
            Image(bitmap = imageFromByteToBitmap, contentDescription = "Food Image ${order.name}")
        }
        Column {
            Text(text = "Name: ${order.name}")
            Text(text = "Price: ${order.price}")
        }
    }
}