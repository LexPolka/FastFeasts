package com.example.a1

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.*
import androidx.compose.ui.unit.sp
import androidx.annotation.StringRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberDrawerState
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.a1.ui.MainPage
import com.example.a1.data.profiledata.ProfileViewModel
import com.example.a1.ui.ProfilePage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import androidx.appcompat.app.AppCompatDelegate
import com.example.a1.data.profiledata.GlobalViewModel
import com.example.a1.ui.InvididualFoodPage
import com.example.inventory.ui.AppViewModelProvider

//enum classes for navigation
enum class FastFeastsScreen(@StringRes val title: Int) {
    MainPage(title = R.string.main_menu),
    Profile(title = R.string.profile),
    Payment(title=R.string.dinein),
    Cart(title=R.string.cart),
    IndividualFood(title= R.string.individual)
}

@Composable
fun FastFeastsApp(
    profileViewModel: ProfileViewModel = viewModel(factory = AppViewModelProvider.Factory),
    globalViewModel: GlobalViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {
    //Navigation variables
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = FastFeastsScreen.valueOf(
        backStackEntry?.destination?.route ?: FastFeastsScreen.MainPage.name
    )

    val isDarkTheme = isSystemInDarkTheme()

    //Side bar variables
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    //Screen settings
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp

    //NAVIGATION DRAWER NEEDS TO BE IN EVERY PAGE!!!!
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet (
                modifier = Modifier.width(screenWidth / 2)
            ){
                Column(
                    Modifier
                        .padding(4.dp)
                ) {
                    //NAVIGATION SIDE BAR
                    //1 HOME
                    Row( modifier = Modifier
                        .padding(bottom = 8.dp)
                        .clickable {
                            scope.launch {
                                if (drawerState.isOpen) drawerState.close() else drawerState.open()
                            }
                            navController.navigate(FastFeastsScreen.MainPage.name)
                        }
                        .background(
                            if (currentScreen == FastFeastsScreen.MainPage) Color.Gray
                            else
                                Color.Transparent
                        )
                        .fillMaxWidth()
                    ) {
                        Image(
                            painter = painterResource(R.drawable.baseline_home_24),
                            contentDescription = "Home",
                            Modifier
                                .size(36.dp)
                                .padding(6.dp)
                                .clip(shape = CircleShape)
                                .background(Color.White)
                        )
                        Text(text = "Home", fontSize = 28.sp)
                    }
                    Divider()
                    //2 PROFILE
                    Row( modifier = Modifier
                        .padding(bottom = 8.dp)
                        .clickable {
                            scope.launch {
                                if (drawerState.isOpen) drawerState.close() else drawerState.open()
                            }
                            navController.navigate(FastFeastsScreen.Profile.name)
                        }
                        .background(
                            if (currentScreen == FastFeastsScreen.Profile) Color.Gray
                            else
                                Color.Transparent
                        )
                        .fillMaxWidth()
                    ) {
                        Image(
                            painter = painterResource(R.drawable.profile),
                            contentDescription = "Profile",
                            Modifier
                                .size(36.dp)
                                .padding(6.dp)
                                .clip(shape = CircleShape)
                                .background(Color.White)
                        )
                        Text(text = "Profile", fontSize = 28.sp)
                    }
                    Divider()
                    //3 LE CART
                    Row( modifier = Modifier
                        .padding(bottom = 8.dp)
                        .clickable {
                            scope.launch {
                                if (drawerState.isOpen) drawerState.close() else drawerState.open()
                            }
                            navController.navigate(FastFeastsScreen.Cart.name)
                        }
                        .background(
                            if (currentScreen == FastFeastsScreen.Cart) Color.Gray
                            else
                                Color.Transparent
                        )
                        .fillMaxWidth()
                    ) {
                        Image(
                            painter = painterResource(R.drawable.cart),
                            contentDescription = "Cart",
                            Modifier
                                .size(36.dp)
                                .padding(6.dp)
                                .clip(shape = CircleShape)
                                .background(Color.White)
                        )
                        Text(text = "Profile", fontSize = 28.sp)
                    }

                    Spacer(Modifier.weight(0.9f))

                    Divider()
                    //DARK MODE
                    Row( modifier = Modifier
                        .padding(bottom = 8.dp)
                        .clickable {
                            toggleDarkMode(isDarkTheme)
                        }
                        .background(
                            if (isDarkTheme) Color.Gray
                            else
                                Color.Transparent
                        )
                        .fillMaxWidth()
                    ) {
                        Image(
                            painter = painterResource(R.drawable.baseline_mode_night_24),
                            contentDescription = "Night Mode",
                            Modifier
                                .size(36.dp)
                                .padding(6.dp)
                                .clip(shape = CircleShape)
                                .background(Color.White)
                        )
                        Text(text = "Dark Mode", fontSize = 28.sp)
                    }
                }
            }
        },
        gesturesEnabled = true,
    ) {
        //EDIT THIS PART TO IMPLEMENT YOUR PAGES/ACTIVITIES
        Scaffold(
            topBar = {
                HeaderBar(scope, drawerState)
            }
        ) { innerPadding ->
            val uiState by profileViewModel.uiState.collectAsState()
            val globalVariables by globalViewModel.foodState.collectAsState()

            NavHost(
                navController = navController,
                startDestination = FastFeastsScreen.MainPage.name,
                modifier = Modifier.padding(innerPadding)
            ) {
                composable(route = FastFeastsScreen.MainPage.name) {
                    MainPage(globalViewModel, navController)
                }
                composable(route = FastFeastsScreen.Profile.name) {
                    ProfilePage(profileViewModel)
                }
                composable(route = FastFeastsScreen.Cart.name) {

                }
                composable(route = FastFeastsScreen.IndividualFood.name) {
                    InvididualFoodPage(globalViewModel, navController)
                }

            }
        }

    }
}

@Composable
fun HeaderBar(scope : CoroutineScope, drawerState : DrawerState) {
    //HEADER BAR CONTENT
    var expanded by remember { mutableStateOf(false) }
    var selectedIndex by remember { mutableIntStateOf(0) }
    val options = listOf("EkoCheras Mall, Jln Cheras", "Setapak Central, Jln Genting Klang", "VivaCity Mall, Jln Kuching", "1Utama, Lebuh Bandar Utama")

    val startColor = Color(0xFFFF9D7E)
    val endColor = Color(0xFF975743)

    val locationSelectWidth = 300.dp

    //Screen settings
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp
    var HeaderBarHeight = (screenHeight*7/100)
    var HeaderComponentHeight = (HeaderBarHeight*85/100)

    //HEADER ======================================================================================================================================
    Surface(
        color = Color.Transparent,
        modifier = Modifier
            .height(HeaderBarHeight)
            .fillMaxWidth()
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(startColor, endColor)
                )
            )
    )
    {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically)
        {
            //SIDE BAR DRAWER START =========================================================================================================================================================================================================
            Surface (
                shape = RoundedCornerShape(10.dp),
                border = BorderStroke(1.dp, Color.Black),
                modifier = Modifier
                    .height(HeaderComponentHeight)
                    .padding(6.dp)) {
                IconButton(
                    onClick = {
                        //scope.launch will launch a coroutine, being:
                        scope.launch {
                            //if drawerState is closed, it will open, if open, it will close
                            if (drawerState.isClosed) drawerState.open() else drawerState.close()
                        }
                    },
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .background(Color.White)
                ) {
                    Icon(
                        imageVector = Icons.Default.List,
                        contentDescription = "Side Bar List",
                        tint = Color.Black,
                        modifier = Modifier.align(Alignment.CenterVertically)
                    )
                }
            }
            //side bar drawer ends
            Spacer(Modifier.weight(0.8f))
            //DROP DOWN LIST START ======================================================================================================================================
            Surface (
                shape = RoundedCornerShape(10.dp),
                border = BorderStroke(1.dp, Color.Black),
                color = Color.White,
                modifier = Modifier
                    .height(HeaderComponentHeight)
                    .width(screenWidth * 85 / 100)
                    .padding(6.dp),
                onClick = { expanded = true }
            ){
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.width(locationSelectWidth)
                )
                {
                    Image(painter = painterResource(id = R.drawable.baseline_location_pin_24),
                        contentDescription = "Location Icon",
                        modifier = Modifier.padding(2.dp))

                    Text(text = options[selectedIndex],
                        color = Color.Black,
                        fontSize = 14.sp
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = "Dropdown",
                        tint = Color.Black,
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .padding(2.dp)
                    )

                    DropdownMenu(
                        modifier =  Modifier.width(locationSelectWidth),
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    )
                    {
                        options.forEachIndexed { index, option ->
                            DropdownMenuItem(
                                text = { Text(option, fontSize = 14.sp) },
                                onClick = {
                                    selectedIndex = index
                                    expanded = false })
                        }
                    }
                }
            }
            //drop down list END
        }
    }
}

@Composable
fun Footer()
{
    val context = LocalContext.current
    val startColor = Color(0xFFFF9D7E)
    val endColor = Color(0xFF975743)

    //design attribtues
    val columnSpacing = 11.dp
    val bigTextSize = 20.sp
    val smallTextSize = 15.sp
    val imageSize = 16.dp
    val buttonPadding = 6.dp

    Surface(
        color = Color.Transparent,
        modifier = Modifier
            .height(150.dp)
            .fillMaxWidth()
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(startColor, endColor)
                )
            )
    ) {
        Row(verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            //LINKS COLUMN ========================
            Column(horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(columnSpacing))
            {
                Text(text = "Links", color = Color.White, fontSize = bigTextSize)

                Row (verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .clickable { }
                        .padding(bottom = buttonPadding)) {
                    Text(text = "Terms and Conditions", color = Color.White, fontSize = smallTextSize)
                }
                Row (verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .clickable { }
                        .padding(bottom = buttonPadding)) {
                    Text(text = "About Us", color = Color.White, fontSize = smallTextSize)
                }
                Row (verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .clickable { }
                        .padding(bottom = buttonPadding)) {
                    Text(text = "Feedback", color = Color.White, fontSize = smallTextSize)
                }

            }

            //CONTACT US COLUMN ========================
            Column(horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(columnSpacing))
            {
                Text(text = "Contact Us", color = Color.White, fontSize = bigTextSize)

                Row (verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .clickable { callNumber(context) }
                        .padding(bottom = buttonPadding)){
                    Image(painter = painterResource(R.drawable.phone), contentDescription = "Contact Us Phone Number",
                        Modifier
                            .size(imageSize)
                            .padding(2.dp)
                    )
                    Text(text = "+60 149966267", color = Color.White, fontSize = smallTextSize)
                }
                Row (verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .clickable { openInstagramProfile(context) }
                        .padding(bottom = buttonPadding)) {
                    Image(painter = painterResource(R.drawable.instagram), contentDescription = "Contact Us Instagram",
                        Modifier
                            .size(imageSize)
                            .padding(2.dp)
                    )
                    Text(text = "FastFeasts.my", color = Color.White, fontSize = smallTextSize)
                }
                Row (verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .clickable { sendEmail(context) }
                        .padding(bottom = buttonPadding)){
                    Image(painter = painterResource(R.drawable.baseline_email_24), contentDescription = "Contact Us Email",
                        Modifier
                            .size(imageSize)
                            .padding(2.dp)
                    )
                    Text(text = "FastFeasts@gmail.com", color = Color.White, fontSize = smallTextSize)
                }
            }

        }
    }
}

private fun sendEmail(context: Context){
    val emailToMail = "FastFeasts@gmail.com"
    //template intent for emailing
    val intent = Intent(Intent.ACTION_SENDTO).apply {
        data = Uri.parse("mailto:")
        putExtra(Intent.EXTRA_EMAIL, arrayOf(emailToMail))
        putExtra(Intent.EXTRA_SUBJECT, "Subject")
        putExtra(Intent.EXTRA_TEXT, "Body")
    }
    context.startActivity(intent)
}

private fun callNumber(context: Context){
    val numberToCall = "+60149966267"
    val intent = Intent(Intent.ACTION_DIAL).apply {
        data = Uri.parse("tel:$numberToCall")
    }
    context.startActivity(intent)
}

private fun openInstagramProfile(context: Context) {
    val instagramUsername = "_lexeon" // Replace with the Instagram username
    val intent = Intent(Intent.ACTION_VIEW).apply {
        data = Uri.parse("https://www.instagram.com/$instagramUsername")
    }
    context.startActivity(intent)
}

fun toggleDarkMode(isDarkTheme : Boolean) {
    AppCompatDelegate.setDefaultNightMode(
        if (isDarkTheme) {
            AppCompatDelegate.MODE_NIGHT_NO // Enable light mode
        } else {
            AppCompatDelegate.MODE_NIGHT_YES // Enable dark mode
        }
    )
}