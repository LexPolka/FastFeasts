package com.example.a1.ui

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.TextField
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.example.a1.R
import android.widget.Toast
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.a1.ui.fastFeast.Footer
import com.example.a1.data.profiledata.ProfileViewModel
import com.example.a1.data.profiledata.Profile
import com.example.a1.data.AppViewModelProvider
import com.example.a1.data.cartData.CartViewModel
import com.example.a1.data.profiledata.GlobalViewModel
import com.example.a1.data.profiledata.ProfileEntity
import com.example.a1.ui.fastFeast.FastFeastsScreen
import kotlinx.coroutines.delay
import androidx.compose.ui.platform.LocalContext
import com.example.a1.ui.fastFeast.imageBitmapFromBytes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfilePage(
    viewModel: ProfileViewModel, navController : NavHostController, globalViewModel: GlobalViewModel, cartViewModel : CartViewModel
) {
    val uiState by viewModel.uiState.collectAsState()
    val context = LocalContext.current

    //toast handler
    var isToastVisible by remember { mutableStateOf(false) }
    if (isToastVisible) {
        LaunchedEffect(Unit) {
            delay(1000) // Adjust the delay as needed
            isToastVisible = false
        }
    }

    Column {
        //profile setter
        ProfileHeader(uiState) { image ->
            val imageInputStream = context.contentResolver.openInputStream(image)
            val imageData = imageInputStream?.readBytes()

            if (imageData != null && imageData.size <= 8000) {
                viewModel.setProfilePictureUri(imageData)
            } else {
                Toast.makeText(context, "Image too Large!", Toast.LENGTH_SHORT).show()
                isToastVisible = true
            }
        }
        ProfileDataModify(viewModel, navController, globalViewModel, cartViewModel)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileDataModify(viewModel : ProfileViewModel, navController: NavHostController, globalViewModel : GlobalViewModel, cartViewModel : CartViewModel) {
    val context = LocalContext.current

    var showDialog by remember { mutableStateOf(false) }

    //toast handler
    var isToastVisible by remember { mutableStateOf(false) }
    if (isToastVisible) {
        LaunchedEffect(Unit) {
            delay(2000) // Adjust the delay as needed
            isToastVisible = false
        }
    }

    val isDarkTheme = isSystemInDarkTheme()

    val uiState by viewModel.uiState.collectAsState()

    val imageSize = 48.dp
    val textSize = 16.sp
    val linePadding = 12.dp

    //Screen settings
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp
    val componentHeight = screenHeight / 9

    //before saving
    val email = uiState.email
    var newName by remember { mutableStateOf(uiState.name) }
    var newDay by remember { mutableStateOf(uiState.day) }
    var newMonth by remember { mutableStateOf(uiState.month) }
    var newYear by remember { mutableStateOf(uiState.year) }
    var newNumber by remember { mutableStateOf(uiState.phoneNumber) }
    var newCCNumber by remember { mutableStateOf(uiState.ccNumber) }
    var newCCMonth by remember { mutableStateOf(uiState.ccMonth) }
    var newCCYear by remember { mutableStateOf(uiState.ccYear) }
    var newCCCode by remember { mutableStateOf(uiState.ccCode) }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Divider(thickness = 3.dp, color = if (isDarkTheme) Color.White else Color.Black)

        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            userScrollEnabled = true,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            items(1) {
                //Name
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .height(componentHeight)
                        .padding(linePadding)
                ) {
                    Image(
                        painter = painterResource(R.drawable.profile), contentDescription = "Name",
                        Modifier
                            .size(imageSize)
                            .padding(6.dp)
                            .clip(shape = CircleShape)
                            .background(Color.White)
                    )
                    TextField(
                        value = newName,
                        onValueChange = { newName = it },
                        label = { Text("Username", color = Color(0xFFFF9D7E)) },
                        maxLines = 1,
                        shape = RoundedCornerShape(16.dp),
                        modifier = Modifier
                            .border(
                                1.5.dp,
                                color = Color.DarkGray,
                                shape = RoundedCornerShape(16.dp)
                            )
                            .fillMaxWidth(),
                        colors = TextFieldDefaults.textFieldColors(
                            focusedIndicatorColor = Color(0xFFFDA6900), // Color when the TextField is focused
                            unfocusedIndicatorColor = Color(0xFFFDA6900), // Color when the TextField is not focused
                            cursorColor = Color(0xFFFF9D7E), // Color of the cursor
                            containerColor = if (isDarkTheme) Color.DarkGray else Color.White,
                        ),
                    )
                    Spacer(modifier = Modifier.weight(1f))
                }

                Divider(
                    thickness = 2.dp,
                    color = Color(0xFFFC8803),
                    modifier = Modifier.padding(horizontal = linePadding)
                )

                //EMAIL
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .height(componentHeight)
                        .padding(linePadding)
                ) {
                    Image(
                        painter = painterResource(R.drawable.baseline_email_24),
                        contentDescription = "Email",
                        Modifier
                            .size(imageSize)
                            .padding(6.dp)
                            .clip(shape = CircleShape)
                            .background(Color.White)
                    )
                    Text(
                        text = email,
                        color = if (isDarkTheme) Color.White else Color.Black,
                        fontSize = textSize
                    )
                    Spacer(modifier = Modifier.weight(1f))
                }

                Divider(
                    thickness = 2.dp,
                    color = Color(0xFFFC8803),
                    modifier = Modifier.padding(horizontal = linePadding)
                )

                //BIRTHDAY
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .height(componentHeight)
                        .padding(linePadding)
                ) {
                    Image(
                        painter = painterResource(R.drawable.baseline_date_range_24),
                        contentDescription = "Birthday",
                        Modifier
                            .size(imageSize)
                            .padding(6.dp)
                            .clip(shape = CircleShape)
                            .background(Color.White)
                    )
                    TextField(
                        value = newDay,
                        onValueChange = { newValue ->
                            if (newValue.all { it.isDigit() }) { //check if any chars
                                val intValue = newValue.toIntOrNull() //convert to int
                                if ((intValue != null && intValue in 1..31) || intValue == null) {
                                    newDay = newValue
                                } else {
                                    newDay = uiState.day
                                    if (!isToastVisible) {
                                        Toast.makeText(
                                            context,
                                            "Day out of range!",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                        isToastVisible = true
                                    }
                                }
                            }
                        },
                        label = { Text("DD", color = Color(0xFFFF9D7E)) },
                        maxLines = 1,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        shape = RoundedCornerShape(16.dp),
                        colors = TextFieldDefaults.textFieldColors(
                            focusedIndicatorColor = Color(0xFFFDA6900), // Color when the TextField is focused
                            unfocusedIndicatorColor = Color(0xFFFDA6900), // Color when the TextField is not focused
                            cursorColor = Color(0xFFFF9D7E), // Color of the cursor
                            containerColor = if (isDarkTheme) Color.DarkGray else Color.White,
                        ),
                        modifier = Modifier
                            .fillParentMaxWidth(0.25f)
                            .padding(2.dp)
                            .border(
                                1.5.dp,
                                color = Color.DarkGray,
                                shape = RoundedCornerShape(16.dp)
                            ),
                    )
                    Divider(
                        thickness = 1.dp,
                        modifier = Modifier
                            .height(componentHeight - 4.dp)
                            .width(1.dp)
                    )
                    TextField(
                        value = newMonth,
                        onValueChange = { newValue ->
                            if (newValue.all { it.isDigit() }) { //check if any chars
                                val intValue = newValue.toIntOrNull() //convert to int
                                if ((intValue != null && intValue in 1..12) || intValue == null) {
                                    newMonth = newValue
                                } else {
                                    newMonth = uiState.month
                                    if (!isToastVisible) {
                                        Toast.makeText(
                                            context,
                                            "Month out of range!",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                        isToastVisible = true
                                    }
                                }
                            }
                        },
                        label = { Text("MM", color = Color(0xFFFF9D7E)) },
                        maxLines = 1,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        shape = RoundedCornerShape(16.dp),
                        colors = TextFieldDefaults.textFieldColors(
                            focusedIndicatorColor = Color(0xFFFDA6900), // Color when the TextField is focused
                            unfocusedIndicatorColor = Color(0xFFFDA6900), // Color when the TextField is not focused
                            cursorColor = Color(0xFFFF9D7E), // Color of the cursor
                            containerColor = if (isDarkTheme) Color.DarkGray else Color.White,
                        ),
                        modifier = Modifier
                            .fillParentMaxWidth(0.25f)
                            .padding(2.dp)
                            .border(
                                1.5.dp,
                                color = Color.DarkGray,
                                shape = RoundedCornerShape(16.dp)
                            ),
                    )
                    Divider(
                        thickness = 1.dp,
                        modifier = Modifier
                            .height(componentHeight - 4.dp)
                            .width(1.dp)
                    )
                    TextField(
                        value = newYear,
                        onValueChange = { newValue ->
                            if (newValue.all { it.isDigit() }) {
                                if (newValue.length < 4) newYear = newValue
                                if (newValue.length == 4) {
                                    val intValue = newValue.toIntOrNull()
                                    if (intValue in 1924..2024) newYear = newValue
                                    else {
                                        newYear = uiState.year
                                        if (!isToastVisible) {
                                            Toast.makeText(
                                                context,
                                                "Year out of range!",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                            isToastVisible = true
                                        }
                                    }
                                }
                            }
                        },
                        label = { Text("YYYY", color = Color(0xFFFF9D7E)) },
                        maxLines = 1,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        shape = RoundedCornerShape(16.dp),
                        colors = TextFieldDefaults.textFieldColors(
                            focusedIndicatorColor = Color(0xFFFDA6900), // Color when the TextField is focused
                            unfocusedIndicatorColor = Color(0xFFFDA6900), // Color when the TextField is not focused
                            cursorColor = Color(0xFFFF9D7E), // Color of the cursor
                            containerColor = if (isDarkTheme) Color.DarkGray else Color.White,
                        ),
                        modifier = Modifier
                            .fillParentMaxWidth(0.3f)
                            .padding(2.dp)
                            .border(
                                1.5.dp,
                                color = Color.DarkGray,
                                shape = RoundedCornerShape(16.dp)
                            ),
                    )
                    Spacer(modifier = Modifier.weight(1f))
                }

                Divider(
                    thickness = 2.dp,
                    color = Color(0xFFFC8803),
                    modifier = Modifier.padding(horizontal = linePadding)
                )

                //PHONE
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .height(componentHeight)
                        .padding(linePadding)
                ) {
                    Image(
                        painter = painterResource(R.drawable.phone),
                        contentDescription = "Phone Number",
                        Modifier
                            .size(imageSize)
                            .padding(6.dp)
                            .clip(shape = CircleShape)
                            .background(Color.White)
                    )
                    TextField(
                        value = newNumber,
                        onValueChange = { newValue ->
                            if (newValue.all { it.isDigit() }) {
                                if (newValue.length <= 10) newNumber = newValue
                                else {
                                    newNumber = uiState.phoneNumber
                                    if (!isToastVisible) {
                                        Toast.makeText(
                                            context,
                                            "Phone Number too long!",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                        isToastVisible = true
                                    }
                                }
                            }
                        },
                        label = { Text("Phone Number", color = Color(0xFFFF9D7E)) },
                        maxLines = 1,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        shape = RoundedCornerShape(16.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(
                                1.5.dp,
                                color = Color.DarkGray,
                                shape = RoundedCornerShape(16.dp)
                            ),
                        colors = TextFieldDefaults.textFieldColors(
                            focusedIndicatorColor = Color(0xFFFDA6900), // Color when the TextField is focused
                            unfocusedIndicatorColor = Color(0xFFFDA6900), // Color when the TextField is not focused
                            cursorColor = Color(0xFFFF9D7E), // Color of the cursor
                            containerColor = if (isDarkTheme) Color.DarkGray else Color.White,
                        ),
                    )
                    Spacer(modifier = Modifier.weight(1f))
                }

                Divider(
                    thickness = 2.dp,
                    color = Color(0xFFFC8803),
                    modifier = Modifier.padding(horizontal = linePadding)
                )

                //CREDIT CARD ROW
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .height(componentHeight * 2.5f)
                        .padding(linePadding)
                ) {
                    Image(
                        painter = painterResource(R.drawable.baseline_add_card_24),
                        contentDescription = "Credit Card",
                        Modifier
                            .size(imageSize)
                            .padding(6.dp)
                            .clip(shape = CircleShape)
                            .background(Color.White)
                    )

                    Column(
                        modifier = Modifier.border(
                            1.5.dp,
                            color = Color.DarkGray,
                            shape = RoundedCornerShape(16.dp)
                        )
                    )
                    {
                        // Credit Card Number
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center,
                            modifier = Modifier
                                .height(componentHeight)
                                .padding(linePadding)
                        ) {

                            TextField(
                                value = newCCNumber,
                                onValueChange = { newValue ->
                                    if (newValue.all { it.isDigit() }) {
                                        if (newValue.length <= 16) newCCNumber = newValue
                                        else {
                                            newCCNumber = uiState.ccNumber
                                            if (!isToastVisible) {
                                                Toast.makeText(
                                                    context,
                                                    "Invalid Credit Card Input",
                                                    Toast.LENGTH_SHORT
                                                ).show()
                                                isToastVisible = true
                                            }
                                        }
                                    }
                                },
                                label = { Text("Credit Card Number", color = Color(0xFFFF9D7E)) },
                                maxLines = 1,
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                                shape = RoundedCornerShape(16.dp),
                                colors = TextFieldDefaults.textFieldColors(
                                    focusedIndicatorColor = Color(0xFFFDA6900), // Color when the TextField is focused
                                    unfocusedIndicatorColor = Color(0xFFFDA6900), // Color when the TextField is not focused
                                    cursorColor = Color(0xFFFF9D7E), // Color of the cursor
                                    containerColor = if (isDarkTheme) Color.DarkGray else Color.White,
                                ),
                            )
                            Spacer(modifier = Modifier.weight(1f))
                        }
                        // Expiry Date and CVV
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center,
                            modifier = Modifier
                                .height(componentHeight)
                                .padding(linePadding)
                        ) {
                            TextField(
                                value = newCCMonth,
                                onValueChange = { newValue ->
                                    if (newValue.all { it.isDigit() }) { //check if any chars
                                        val intValue = newValue.toIntOrNull() //convert to int
                                        if ((intValue != null && intValue in 1..12) || intValue == null) {
                                            newCCMonth = newValue
                                        } else {
                                            newCCMonth = uiState.ccMonth
                                            if (!isToastVisible) {
                                                Toast.makeText(
                                                    context,
                                                    "Month out of range!",
                                                    Toast.LENGTH_SHORT
                                                ).show()
                                                isToastVisible = true
                                            }
                                        }
                                    }
                                },
                                label = { Text("MM", color = Color(0xFFFF9D7E)) },
                                maxLines = 1,
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                                shape = RoundedCornerShape(16.dp),
                                colors = TextFieldDefaults.textFieldColors(
                                    focusedIndicatorColor = Color(0xFFFDA6900), // Color when the TextField is focused
                                    unfocusedIndicatorColor = Color(0xFFFDA6900), // Color when the TextField is not focused
                                    cursorColor = Color(0xFFFF9D7E), // Color of the cursor
                                    containerColor = if (isDarkTheme) Color.DarkGray else Color.White,
                                ),
                                modifier = Modifier
                                    .fillParentMaxWidth(0.25f)
                                    .padding(2.dp)
                            )
                            Divider(
                                thickness = 1.dp,
                                modifier = Modifier
                                    .height(componentHeight - 4.dp)
                                    .width(1.dp)
                            )
                            TextField(
                                value = newCCYear,
                                onValueChange = { newValue ->
                                    if (newValue.all { it.isDigit() }) {
                                        if (newValue.length < 4) newCCYear = newValue
                                        if (newValue.length == 4) {
                                            val intValue = newValue.toIntOrNull()
                                            if (intValue != null && intValue >= 2024) newCCYear = newValue
                                            else {
                                                newCCYear = uiState.year
                                                if (!isToastVisible) {
                                                    Toast.makeText(
                                                        context,
                                                        "Year must be 2024 or later!",
                                                        Toast.LENGTH_SHORT
                                                    ).show()
                                                    isToastVisible = true
                                                }
                                            }
                                        }
                                    }
                                },
                                label = { Text("YYYY", color = Color(0xFFFF9D7E)) },
                                maxLines = 1,
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                                shape = RoundedCornerShape(16.dp),
                                colors = TextFieldDefaults.textFieldColors(
                                    focusedIndicatorColor = Color(0xFFFDA6900), // Color when the TextField is focused
                                    unfocusedIndicatorColor = Color(0xFFFDA6900), // Color when the TextField is not focused
                                    cursorColor = Color(0xFFFF9D7E), // Color of the cursor
                                    containerColor = if (isDarkTheme) Color.DarkGray else Color.White,
                                ),
                                modifier = Modifier
                                    .fillParentMaxWidth(0.25f)
                                    .padding(2.dp)
                            )
                            Divider(
                                thickness = 1.dp,
                                modifier = Modifier
                                    .height(componentHeight - 4.dp)
                                    .width(1.dp)
                            )
                            TextField(
                                value = newCCCode,
                                onValueChange = { newValue ->
                                    if (newValue.all { it.isDigit() }) {
                                        if (newValue.length <= 3) newCCCode = newValue
                                        else {
                                            newCCCode = uiState.ccCode
                                            if (!isToastVisible) {
                                                Toast.makeText(
                                                    context,
                                                    "Invalid CCV Length!",
                                                    Toast.LENGTH_SHORT
                                                ).show()
                                                isToastVisible = true
                                            }
                                        }
                                    }
                                },
                                label = { Text("CVV", color = Color(0xFFFF9D7E)) },
                                maxLines = 1,
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                                shape = RoundedCornerShape(16.dp),
                                colors = TextFieldDefaults.textFieldColors(
                                    focusedIndicatorColor = Color(0xFFFDA6900), // Color when the TextField is focused
                                    unfocusedIndicatorColor = Color(0xFFFDA6900), // Color when the TextField is not focused
                                    cursorColor = Color(0xFFFF9D7E), // Color of the cursor
                                    containerColor = if (isDarkTheme) Color.DarkGray else Color.White,
                                ),
                                modifier = Modifier
                                    .fillParentMaxWidth(0.25f)
                                    .padding(2.dp)
                            )
                        }
                    }
                }

                Divider(
                    thickness = 2.dp,
                    color = Color(0xFFFC8803),
                    modifier = Modifier.padding(horizontal = linePadding)
                )

                //APPLY / CANCEL
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .height(componentHeight)
                        .padding(linePadding)
                ) {
                    IconButton(
                        colors = IconButtonDefaults.iconButtonColors(Color.White),
                        onClick = {
                            newName = uiState.name
                            newDay = uiState.day
                            newMonth = uiState.month
                            newYear = uiState.year
                            newNumber = uiState.phoneNumber
                            if (!isToastVisible) {
                                Toast.makeText(context, "Cancelled Changes.", Toast.LENGTH_SHORT)
                                    .show()
                                isToastVisible = true
                            }
                        },
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
                            val CCYear = newCCYear.toIntOrNull()
                            if (newCCNumber.isNotBlank() && newCCNumber.length < 16) {
                                if (!isToastVisible) {
                                    Toast.makeText(context,"Invalid Credit Card!",Toast.LENGTH_SHORT).show()
                                    isToastVisible = true
                                }
                            }
                            if (CCYear != null && CCYear < 2024) {
                                if (!isToastVisible) {
                                    Toast.makeText(context,"Invalid Card Year!",Toast.LENGTH_SHORT).show()
                                    isToastVisible = true
                                }
                            }
                            if (newCCCode.isNotBlank() && newCCCode.length < 3) {
                                if (!isToastVisible) {
                                    Toast.makeText(context,"Invalid Card CVV!",Toast.LENGTH_SHORT).show()
                                    isToastVisible = true
                                }
                            }
                            if (newYear.toIntOrNull() != null && newYear.toIntOrNull() !in 1934..2024) {
                                if (!isToastVisible) {
                                    Toast.makeText(
                                        context,
                                        "Year out of range!",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    isToastVisible = true
                                }
                            } else if (newNumber.toIntOrNull() != null && newNumber.length < 10) {
                                if (!isToastVisible) {
                                    Toast.makeText(
                                        context,
                                        "Phone Number too short!",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    isToastVisible = true
                                }
                            } else {
                                //UISTATE
                                viewModel.setName(newName)
                                viewModel.setDate(newDay, newMonth, newYear)
                                viewModel.setPhone(newNumber)
                                viewModel.setCreditCard(newCCNumber, newCCCode, newCCMonth, newCCYear)

                                //DATABASE
                                viewModel.saveProfile()

                                if (!isToastVisible) {
                                    Toast.makeText(
                                        context,
                                        "Changes have been Applied.",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    isToastVisible = true
                                }
                            }

                        },
                        modifier = Modifier
                            .padding(8.dp)
                            .border(3.5.dp, Color.White, shape = CircleShape)
                            .width(100.dp)
                    ) {
                        Text("Apply", color = Color.White)
                    }
                }
                Divider(thickness = 3.dp, color = if (isDarkTheme) Color.White else Color.Black)
                Spacer(Modifier.weight(0.3f))

                //Logout Account
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .height(componentHeight)
                        .padding(linePadding)
                        .clickable {
                            cartViewModel.clearFoodCart()
                            navController.navigate(FastFeastsScreen.LoginScreen.name)
                            globalViewModel.loggedIn(false)
                        }
                ) {
                    Image(
                        painter = painterResource(R.drawable.baseline_person_remove_24), contentDescription = "Name",
                        Modifier
                            .size(imageSize)
                            .padding(6.dp)
                            .clip(shape = CircleShape)
                            .background(Color.White)
                    )
                    Text(text = "Log Out of Account", color = Color.Red, modifier = Modifier.background(color = Color.LightGray))
                    Spacer(modifier = Modifier.weight(1f))
                }
                Divider(
                    thickness = 2.dp,
                    color = Color(0xFFFC8803),
                    modifier = Modifier.padding(horizontal = linePadding)
                )
                //Delete Account
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .height(componentHeight)
                        .padding(linePadding)
                        .clickable {
                            showDialog = true
                        }
                ) {
                    Image(
                        painter = painterResource(R.drawable.baseline_delete_forever_24), contentDescription = "Name",
                        Modifier
                            .size(imageSize)
                            .padding(6.dp)
                            .clip(shape = CircleShape)
                            .background(Color.White)
                    )
                    Text(text = "DELETE Account", color = Color.Red, modifier = Modifier.background(color = Color.LightGray))
                    Spacer(modifier = Modifier.weight(1f))
                }

                Spacer(Modifier.weight(0.3f))
                Divider(thickness = 3.dp, color = if (isDarkTheme) Color.White else Color.Black)
                Footer()
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

                Text("Delete Account?", fontSize = 20.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(top = 4.dp))
                Text("Account Deletion is Permanent.", fontSize = 16.sp)
                Text("All personal data is wiped.", fontSize = 16.sp)

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
                            cartViewModel.clearFoodCart()
                            viewModel.deleteProfile(email)
                            navController.navigate(FastFeastsScreen.LoginScreen.name)
                            globalViewModel.loggedIn(false)
                            showDialog = false
                        },
                        modifier = Modifier
                            .padding(8.dp)
                            .border(3.5.dp, Color.White, shape = CircleShape)
                            .width(100.dp)
                    ) {
                        Text("Delete", color = Color.White)
                    }
                }
            }
        }
    }
}

@Composable
fun ProfileHeader(
    uiState: ProfileEntity,
    onProfilePictureSelected: (Uri) -> Unit
) {
    val startColor = Color(0xFFFF9D7E)
    val endColor = Color(0xFF975743)

    // Remember the launcher for activity result
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        if (uri != null) {
            onProfilePictureSelected(uri)
        }
    }

    //Screen settings
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp

    Surface(
        color = Color.Transparent,
        modifier = Modifier
            .height(screenHeight / 4.5f)
            .fillMaxWidth()
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(startColor, endColor)
                )
            )
    ) {
        //Box for setting profile picture
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .clickable {
                    launcher.launch("image/*")
                }
                .clip(shape = CircleShape)
                .fillMaxHeight(0.5f)
        ) {
            ProfileIcon(uiState.profilePictureUri)
        }
    }
}

@Composable
fun ProfileIcon(
    profilePicture: ByteArray?
) {
    val painter: Painter = rememberImagePainter(
        data = profilePicture,
        builder = {
            transformations(CircleCropTransformation()) // Crop the image into a circle
        }
    )

    val context = LocalContext.current

    //toast handler
    var isToastVisible by remember { mutableStateOf(false) }
    if (isToastVisible) {
        LaunchedEffect(Unit) {
            delay(1000) // Adjust the delay as needed
            isToastVisible = false
        }
    }

    if (profilePicture != null && profilePicture.isNotEmpty()) {
        Image(
            painter = painter,
            contentDescription = "Profile Picture",
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape)
                .border(3.dp, color = Color.DarkGray, shape = CircleShape)
        )
    } else {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape)
                .background(Color.White)
                .border(3.dp, color = Color.DarkGray, shape = CircleShape)
        ) {
            Icon(
                imageVector = Icons.Filled.Person,
                contentDescription = "Default Profile Picture",
                modifier = Modifier
                    .size(80.dp)
                    .align(Alignment.Center)
            )
        }
    }
}