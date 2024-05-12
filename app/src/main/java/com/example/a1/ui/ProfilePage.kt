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
import androidx.compose.ui.platform.LocalContext
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.a1.data.profiledata.ProfileViewModel
import com.example.a1.data.profiledata.Profile
import com.example.a1.data.profiledata.ProfileDao
import com.example.a1.data.profiledata.ProfileEntity
import com.example.inventory.ui.AppViewModelProvider
import kotlinx.coroutines.delay


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfilePage(
    viewModel: ProfileViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val context = LocalContext.current
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
    val textSize = 20.sp

    //Screen settings
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp
    val componentHeight = screenHeight/10

    //before saving
    var newName by remember { mutableStateOf(uiState.name)}
    var newDay by remember { mutableStateOf(uiState.day)}
    var newMonth by remember { mutableStateOf(uiState.month)}
    var newYear by remember { mutableStateOf(uiState.year)}
    var newNumber by remember { mutableStateOf(uiState.phoneNumber)}

    Column (horizontalAlignment = Alignment.CenterHorizontally) {
        //profile setter
        ProfileHeader(uiState) { uri ->
            viewModel.setProfilePictureUri(uri)
        }

        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            userScrollEnabled = true,
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth()
        ) {
            items(1) {
                //Name
                Row (verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.height(componentHeight)
                ) {
                    Image(painter = painterResource(R.drawable.profile), contentDescription = "Name",
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
                        colors = TextFieldDefaults.colors(
                            focusedTextColor = Color.White,
                            unfocusedTextColor = Color.White,
                            focusedIndicatorColor = Color(0xFFFF9D7E), // Color when the TextField is focused
                            unfocusedIndicatorColor = Color(0xFFFF9D7E), // Color when the TextField is not focused
                            cursorColor = Color(0xFFFF9D7E), // Color of the cursor
                            focusedContainerColor = Color.DarkGray,
                            unfocusedContainerColor = Color.DarkGray
                        ),
                    )
                    Spacer(modifier = Modifier.weight(1f))
                }

                Divider(thickness = 2.dp)

                //EMAIL
                Row (verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.height(componentHeight)
                ) {
                    Image(painter = painterResource(R.drawable.baseline_email_24), contentDescription = "Email",
                        Modifier
                            .size(imageSize)
                            .padding(6.dp)
                            .clip(shape = CircleShape)
                            .background(Color.White)
                    )
                    Text(text = "FastFeasts@gmail.com", color = if(isDarkTheme)Color.White else Color.Black, fontSize = textSize)
                    Spacer(modifier = Modifier.weight(1f))
                }

                Divider(thickness = 2.dp)

                //BIRTHDAY
                Row (verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.height(componentHeight)
                ) {
                    Image(painter = painterResource(R.drawable.baseline_date_range_24), contentDescription = "Birthday",
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
                                }
                                else
                                {
                                    newDay = uiState.day
                                    if (!isToastVisible) {
                                        Toast.makeText(context, "Day out of range!", Toast.LENGTH_SHORT).show()
                                        isToastVisible = true
                                    }
                                }
                            }
                        },
                        label = { Text("DD", color = Color(0xFFFF9D7E)) },
                        maxLines = 1,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        shape = RoundedCornerShape(16.dp),
                        colors = TextFieldDefaults.colors(
                            focusedTextColor = Color.White,
                            unfocusedTextColor = Color.White,
                            focusedIndicatorColor = Color(0xFFFF9D7E), // Color when the TextField is focused
                            unfocusedIndicatorColor = Color(0xFFFF9D7E), // Color when the TextField is not focused
                            cursorColor = Color(0xFFFF9D7E), // Color of the cursor
                            focusedContainerColor = Color.DarkGray,
                            unfocusedContainerColor = Color.DarkGray
                        ),
                        modifier = Modifier.fillParentMaxWidth(0.25f).padding(2.dp)
                    )
                    Divider(thickness = 1.dp,
                        modifier = Modifier
                        .height(componentHeight-4.dp)
                        .width(1.dp))
                    TextField(
                        value = newMonth,
                        onValueChange = { newValue ->
                            if (newValue.all { it.isDigit() }) { //check if any chars
                                val intValue = newValue.toIntOrNull() //convert to int
                                if ((intValue != null && intValue in 1..12) || intValue == null) {
                                    newMonth = newValue
                                }
                                else
                                {
                                    newMonth = uiState.month
                                    if (!isToastVisible) {
                                        Toast.makeText(context, "Month out of range!", Toast.LENGTH_SHORT).show()
                                        isToastVisible = true
                                    }
                                }
                            }
                        },
                        label = { Text("MM", color = Color(0xFFFF9D7E)) },
                        maxLines = 1,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        shape = RoundedCornerShape(16.dp),
                        colors = TextFieldDefaults.colors(
                            focusedTextColor = Color.White,
                            unfocusedTextColor = Color.White,
                            focusedIndicatorColor = Color(0xFFFF9D7E), // Color when the TextField is focused
                            unfocusedIndicatorColor = Color(0xFFFF9D7E), // Color when the TextField is not focused
                            cursorColor = Color(0xFFFF9D7E), // Color of the cursor
                            focusedContainerColor = Color.DarkGray,
                            unfocusedContainerColor = Color.DarkGray
                        ),
                        modifier = Modifier.fillParentMaxWidth(0.25f).padding(2.dp)
                    )
                    Divider(thickness = 1.dp,
                        modifier = Modifier
                            .height(componentHeight-4.dp)
                            .width(1.dp))
                    TextField(
                        value = newYear,
                        onValueChange = { newValue ->
                            if (newValue.all { it.isDigit() })
                            {
                                if (newValue.length < 4) newYear = newValue
                                if (newValue.length == 4)
                                {
                                    val intValue = newValue.toIntOrNull()
                                    if (intValue in 1924..2024) newYear = newValue
                                    else{
                                        newYear = uiState.year
                                        if (!isToastVisible) {
                                            Toast.makeText(context, "Year out of range!", Toast.LENGTH_SHORT).show()
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
                            focusedTextColor = Color.White,
                            unfocusedTextColor = Color.White,
                            focusedIndicatorColor = Color(0xFFFF9D7E), // Color when the TextField is focused
                            unfocusedIndicatorColor = Color(0xFFFF9D7E), // Color when the TextField is not focused
                            cursorColor = Color(0xFFFF9D7E), // Color of the cursor
                            containerColor = Color.DarkGray
                        ),
                        modifier = Modifier
                            .fillParentMaxWidth(0.3f).padding(2.dp)
                    )
                    Spacer(modifier = Modifier.weight(1f))
                }

                Divider(thickness = 2.dp)

                //PHONE
                Row (verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.height(componentHeight)
                ) {
                    Image(painter = painterResource(R.drawable.phone), contentDescription = "Phone Number",
                        Modifier
                            .size(imageSize)
                            .padding(6.dp)
                            .clip(shape = CircleShape)
                            .background(Color.White)
                    )
                    TextField(
                        value = newNumber,
                        onValueChange = { newValue ->
                            if (newValue.all { it.isDigit() })
                            {
                                if (newValue.length <= 10) newNumber = newValue
                                else
                                {
                                    newNumber = uiState.phoneNumber
                                    if (!isToastVisible) {
                                        Toast.makeText(context, "Phone Number too long!", Toast.LENGTH_SHORT).show()
                                        isToastVisible = true
                                    }
                                }
                            }
                        },
                        label = { Text("Phone Number", color = Color(0xFFFF9D7E)) },
                        maxLines = 1,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        shape = RoundedCornerShape(16.dp),
                        colors = TextFieldDefaults.colors(
                            focusedTextColor = Color.White,
                            unfocusedTextColor = Color.White,
                            focusedIndicatorColor = Color(0xFFFF9D7E), // Color when the TextField is focused
                            unfocusedIndicatorColor = Color(0xFFFF9D7E), // Color when the TextField is not focused
                            cursorColor = Color(0xFFFF9D7E), // Color of the cursor
                            focusedContainerColor = Color.DarkGray,
                            unfocusedContainerColor = Color.DarkGray
                        ),
                    )
                    Spacer(modifier = Modifier.weight(1f))
                }

                Divider(thickness = 2.dp)

                //APPLY / CANCEL
                Row (verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.height(componentHeight)
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
                                Toast.makeText(context, "Cancelled Changes.", Toast.LENGTH_SHORT).show()
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
                            if (newYear.toIntOrNull() != null && newYear.toIntOrNull() !in 1934..2024)
                            {
                                if (!isToastVisible) {
                                    Toast.makeText(context, "Year out of range!", Toast.LENGTH_SHORT).show()
                                    isToastVisible = true
                                }
                            }
                            else if (newNumber.toIntOrNull() != null && newNumber.length < 10) {
                                if (!isToastVisible) {
                                    Toast.makeText(context, "Phone Number too short!", Toast.LENGTH_SHORT).show()
                                    isToastVisible = true
                                }
                            }
                            else
                            {
                                //UISTATE
                                viewModel.setName(newName)
                                viewModel.setDate(newDay, newMonth, newYear)
                                viewModel.setPhone(newNumber)

                                //DATABASE
                                viewModel.saveProfile()

                                if (!isToastVisible) {
                                    Toast.makeText(context, "Changes have been Applied.", Toast.LENGTH_SHORT).show()
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

            }
        }
    }
}

@Composable
fun ProfileHeader(
    uiState: Profile,
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
    profilePictureUri: String
) {
    val painter: Painter = rememberImagePainter(
        data = profilePictureUri,
        builder = {
            transformations(CircleCropTransformation()) // Crop the image into a circle
        }
    )

    if (!profilePictureUri.isBlank()) {
        Image(
            painter = painter,
            contentDescription = "Profile Picture",
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape)
        )
    } else {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape)
                .background(Color.White)
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
