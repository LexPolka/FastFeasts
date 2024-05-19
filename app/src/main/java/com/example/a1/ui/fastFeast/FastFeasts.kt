package com.example.a1.ui.fastFeast

import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
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
import com.example.a1.data.profiledata.ProfileViewModel
import com.example.a1.ui.ProfilePage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.navigation
import com.example.a1.R
import com.example.a1.data.cartData.CartViewModel
import com.example.a1.data.AppViewModelProvider
import com.example.a1.data.CustomizeFood.StockViewModel
import com.example.a1.data.profiledata.GlobalViewModel
import com.example.a1.data.staffdata.StaffViewModel
import com.example.a1.ui.CartUi
import com.example.a1.ui.CustomizeFood.CustomizationScreen
import com.example.a1.ui.DiningOptions
import com.example.a1.ui.OnlineBankingUi
import com.example.a1.ui.PayAtCounterUi
import com.example.a1.ui.PaymentOptions
import com.example.a1.ui.staffUI.StaffPage
import com.example.a1.ui.login.LoginScreen
import com.example.a1.ui.login.PrivacyPolicy
import com.example.a1.ui.login.SignUpScreen
import com.example.a1.ui.login.TermsAndConditions
import com.example.a1.ui.staffUI.StaffIndividualFood
import com.example.a1.ui.staffUI.StaffOrders
import com.example.a1.ui.staffUI.StaffStocks
import kotlinx.coroutines.delay
import java.io.ByteArrayOutputStream
import java.util.zip.Deflater
import java.util.zip.Inflater

//enum classes for navigation
enum class FastFeastsScreen(@StringRes val title: Int) {
    MainPage(title = R.string.main_menu),
    CustomizeBurger(title = R.string.CustomizeBurger),
    Profile(title = R.string.profile),
    Payment(title= R.string.dinein),
    Cart(title= R.string.cart),
    IndividualFood(title= R.string.individual),

    LoginScreen(title = R.string.Login),
    SignUpScreen(title = R.string.Register),
    PrivacyScreen(title = R.string.Privacy),
    PolicyScreen(title = R.string.Terms),

    DiningOptions(title = R.string.dining_options),
    PaymentOptions(title= R.string.payment_options),
    OnlineBanking(title = R.string.online_banking),
    PayAtCounter(title = R.string.pay_at_counter),

    Staff(title = R.string.staff),
    StaffIndividualFood(title= R.string.staffFood),
    StaffStocks(title = R.string.staffStocks),
    StaffOrders(title = R.string.staffOrders),
}

@Composable
fun FastFeastsApp(
    profileViewModel: ProfileViewModel = viewModel(factory = AppViewModelProvider.Factory),
    staffViewModel: StaffViewModel = viewModel(factory = AppViewModelProvider.Factory),
    globalViewModel: GlobalViewModel = viewModel(),
    cartViewModel : CartViewModel = viewModel(factory = AppViewModelProvider.Factory),
    navController: NavHostController = rememberNavController()
) {
    //Navigation variables
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = FastFeastsScreen.valueOf(
        backStackEntry?.destination?.route ?: FastFeastsScreen.MainPage.name
    )

    //Side bar variables
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    //Screen settings
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp

    val imageSize = 40.dp
    val imagePadding = 4.dp
    val verticalPadding = 10.dp

    val uiState by profileViewModel.uiState.collectAsState()
    val globalVariables by globalViewModel.foodState.collectAsState()
    val isLoggedIn by globalViewModel.variables.collectAsState()

    val isDarkTheme = isSystemInDarkTheme()
    var darkMode by remember { mutableStateOf(isDarkTheme) }

    fun toggleDarkMode() {
        darkMode = !darkMode
        val mode = if (isDarkTheme) {
            AppCompatDelegate.MODE_NIGHT_YES // Enable dark mode
        } else {
            AppCompatDelegate.MODE_NIGHT_NO // Enable light mode
        }
        AppCompatDelegate.setDefaultNightMode(mode)
    }

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
                        .padding(vertical = verticalPadding)
                        .clickable {
                            scope.launch {
                                if (drawerState.isOpen) drawerState.close() else drawerState.open()
                            }
                            navController.navigate(FastFeastsScreen.MainPage.name)
                        }
                        .background(
                            if (currentScreen == FastFeastsScreen.MainPage) Color.Gray
                            else
                                Color.Transparent, shape = RoundedCornerShape(5.dp)
                        )
                        .fillMaxWidth()
                    ) {
                        Image(
                            painter = painterResource(R.drawable.baseline_home_24),
                            contentDescription = "Home",
                            Modifier
                                .size(imageSize)
                                .padding(imagePadding)
                                .clip(shape = CircleShape)
                                .background(Color.White)
                        )
                        Text(text = "Home", fontSize = 28.sp)
                    }
                    Divider()
                    //2 PROFILE
                    Row( modifier = Modifier
                        .padding(vertical = verticalPadding)
                        .clickable {
                            scope.launch {
                                if (drawerState.isOpen) drawerState.close() else drawerState.open()
                            }
                            navController.navigate(FastFeastsScreen.Profile.name)
                        }
                        .background(
                            if (currentScreen == FastFeastsScreen.Profile) Color.Gray
                            else
                                Color.Transparent, shape = RoundedCornerShape(5.dp)
                        )
                        .fillMaxWidth()
                    ) {
                        Image(
                            painter = painterResource(R.drawable.profile),
                            contentDescription = "Profile",
                            Modifier
                                .size(imageSize)
                                .padding(imagePadding)
                                .clip(shape = CircleShape)
                                .background(Color.White)
                        )
                        Text(text = "Profile", fontSize = 28.sp)
                    }
                    Divider()
                    //3 STAFF (not visible if user is User and Not Staff)
                    if (uiState.isStaff)
                    {
                        Row( modifier = Modifier
                            .padding(vertical = verticalPadding)
                            .clickable {
                                scope.launch {
                                    if (drawerState.isOpen) drawerState.close() else drawerState.open()
                                }
                                navController.navigate(FastFeastsScreen.Staff.name)
                            }
                            .background(
                                if (currentScreen == FastFeastsScreen.Staff) Color.Gray
                                else
                                    Color.Transparent, shape = RoundedCornerShape(5.dp)
                            )
                            .fillMaxWidth()
                        ) {
                            Image(
                                painter = painterResource(R.drawable.baseline_assignment_ind_24),
                                contentDescription = "Staff",
                                Modifier
                                    .size(imageSize)
                                    .padding(imagePadding)
                                    .clip(shape = CircleShape)
                                    .background(Color.White)
                            )
                            Text(text = "Staff", fontSize = 28.sp)
                        }
                    }

                    Divider()
                    //4 LE CART
                    Row( modifier = Modifier
                        .padding(vertical = verticalPadding)
                        .clickable {
                            scope.launch {
                                if (drawerState.isOpen) drawerState.close() else drawerState.open()
                            }
                            navController.navigate(FastFeastsScreen.Cart.name)
                        }
                        .background(
                            if (currentScreen == FastFeastsScreen.Cart) Color.Gray
                            else
                                Color.Transparent, shape = RoundedCornerShape(5.dp)
                        )
                        .fillMaxWidth()
                    ) {
                        Image(
                            painter = painterResource(R.drawable.cart),
                            contentDescription = "Cart",
                            Modifier
                                .size(imageSize)
                                .padding(imagePadding)
                                .clip(shape = CircleShape)
                                .background(Color.White)
                        )
                        Text(text = "Cart", fontSize = 28.sp)
                    }

                    Spacer(Modifier.weight(0.9f))

                    Divider()
                    //DARK MODE
                    Row( modifier = Modifier
                        .padding(bottom = 8.dp)
                        .clickable {
                            toggleDarkMode()
                        }
                        .background(
                            if (isDarkTheme) Color.Gray
                            else
                                Color.Transparent, shape = RoundedCornerShape(5.dp)
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
        gesturesEnabled = isLoggedIn.isLoggedIn,
    ) {
        //EDIT THIS PART TO IMPLEMENT YOUR PAGES/ACTIVITIES
        Scaffold(
            topBar = {
                if (isLoggedIn.isLoggedIn){
                    HeaderBar(globalViewModel, scope, drawerState)
                }
            }
        ) { innerPadding ->


            //NAV HOST IS HERE =============================
            NavHost(
                navController = navController,
                startDestination = "login_flow", //replace login_flow with FastFeastsScreen.MainPage.name
                modifier = Modifier.padding(innerPadding)
            ) {
                composable(route = FastFeastsScreen.MainPage.name) {
                    MainPage(staffViewModel, globalViewModel, navController)
                }
                composable(route = FastFeastsScreen.Profile.name) {
                    ProfilePage(profileViewModel, navController, globalViewModel, cartViewModel)
                }
                composable(route = FastFeastsScreen.Cart.name) {
                    CartUi(navController, modifier = Modifier, cartViewModel)
                }
                composable(route = FastFeastsScreen.IndividualFood.name) {
                    IndividualFoodPage(cartViewModel, globalViewModel, navController)
                }
                composable(route = FastFeastsScreen.DiningOptions.name) {
                    DiningOptions(navController)
                }
                composable(route = FastFeastsScreen.PaymentOptions.name) {
                    PaymentOptions(profileViewModel, navController)
                }
                composable(route = FastFeastsScreen.CustomizeBurger.name) {
                    CustomizationScreen(navController)
                }

                composable(route = FastFeastsScreen.Staff.name) {
                    StaffPage(navController)
                }
                composable(route = FastFeastsScreen.StaffIndividualFood.name) {
                    StaffIndividualFood(staffViewModel, navController)
                }
                composable(route = FastFeastsScreen.StaffStocks.name) {
                    StaffStocks(staffViewModel, navController)
                }
                composable(route = FastFeastsScreen.StaffOrders.name) {
                    StaffOrders(staffViewModel, navController)
                }

                //depending on userInput from paymentOptions , to be implemented
                composable(route = FastFeastsScreen.OnlineBanking.name) {
                    OnlineBankingUi(staffViewModel, cartViewModel, navController, globalViewModel)
                }
                composable(route = FastFeastsScreen.PayAtCounter.name) {
                    PayAtCounterUi(staffViewModel, cartViewModel, navController, globalViewModel)
                }

                navigation(startDestination = FastFeastsScreen.LoginScreen.name , route = "login_flow" ) {
                    composable(route = FastFeastsScreen.LoginScreen.name) {
                        LoginScreen(
                            profileViewModel,
                            onLoginClick = {
                                navController.navigate(
                                    FastFeastsScreen.MainPage.name
                                ){
                                    popUpTo(route = "login_flow")
                                }

                                globalViewModel.loggedIn(true)
                            },
                            onSignUpClick = {
                                navController.navigateToSingleTop(
                                    FastFeastsScreen.SignUpScreen.name
                                )
                            }
                        )
                    }
                    composable(route = FastFeastsScreen.SignUpScreen.name) {
                        SignUpScreen(
                            profileViewModel,
                            onSignUpClick = {
                                navController.navigate(
                                    FastFeastsScreen.MainPage.name
                                ){
                                    popUpTo("login_flow")
                                }
                                globalViewModel.loggedIn(true)
                            },
                            onLoginClick = {
                                navController.navigateToSingleTop(
                                    FastFeastsScreen.LoginScreen.name
                                )
                            },
                            onPrivacyClick = {
                                navController.navigate(
                                    FastFeastsScreen.PrivacyScreen.name
                                ){
                                    launchSingleTop = true
                                }
                            },
                            onPolicyClick = {
                                navController.navigate(
                                    FastFeastsScreen.PolicyScreen.name
                                ){
                                    launchSingleTop = true
                                }
                            }
                        )
                    }
                    composable(route = FastFeastsScreen.PrivacyScreen.name) {
                        TermsAndConditions( {navController.navigateUp() })
                    }
                    composable(route = FastFeastsScreen.PolicyScreen.name) {
                        PrivacyPolicy ({ navController.navigateUp() })
                    }
                }
            }
        }

    }
}

fun NavController.navigateToSingleTop(route:String){
    navigate(route){
        popUpTo(graph.findStartDestination().id){
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}

@Composable
fun HeaderBar(globalViewModel : GlobalViewModel ,scope : CoroutineScope, drawerState : DrawerState) {
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
                                    globalViewModel.setLocation(options[selectedIndex])
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
            .height(200.dp)
            .fillMaxWidth()
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(startColor, endColor)
                )
            )
    ) {
        Row(verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
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

@Composable
fun BackButton(navController : NavHostController){
    val isDarkTheme = isSystemInDarkTheme()

    //Screen settings
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val backButtonWidth = screenWidth / 3

    //toast handler
    var isToastVisible by remember { mutableStateOf(false) }
    if (isToastVisible) {
        LaunchedEffect(Unit) {
            delay(500) // Adjust the delay as needed
            isToastVisible = false
        }
    }
    //Back button handler
    var isBackPressed by remember { mutableStateOf(false) }
    if (isBackPressed) {
        LaunchedEffect(Unit) {
            delay(2000) // Adjust the delay as needed
            isBackPressed = false
        }
    }

    val universalPadding = 8.dp

    Row {
        IconButton(
            colors = IconButtonDefaults.iconButtonColors(Color(0xFFFF9D7E)),
            onClick = { if (!isBackPressed) {
                navController.popBackStack()
                isBackPressed = true } },
            modifier = Modifier
                .padding(universalPadding)
                .background(color = if (isDarkTheme) Color.White else Color(0xFFFDA6900),
                    shape = RoundedCornerShape(24.dp))
                .border(
                    3.dp,
                    color = if (isDarkTheme) Color.White else Color(0xFFFDA6900),
                    shape = RoundedCornerShape(24.dp)
                )
                .width(backButtonWidth)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    tint = Color.White,
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Back Button",
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(universalPadding)
                )
                Spacer(modifier = Modifier.weight(0.1f))
                Text("Back",color = if (isDarkTheme) Color.White else Color.Black,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(universalPadding)
                )
                Spacer(modifier = Modifier.weight(0.1f))
            }
        }

        IconButton(
            colors = IconButtonDefaults.iconButtonColors(Color(0xFFFF9D7E)),
            onClick = { if (!isBackPressed) {
                navController.navigate(FastFeastsScreen.MainPage.name)
                isBackPressed = true } },
            modifier = Modifier
                .padding(universalPadding)
                .background(color = if (isDarkTheme) Color.White else Color(0xFFFDA6900),
                    shape = RoundedCornerShape(24.dp))
                .border(
                    3.dp,
                    color = if (isDarkTheme) Color.White else Color(0xFFFDA6900),
                    shape = RoundedCornerShape(24.dp)
                )
        ) {
            Icon(
                tint = Color.White,
                imageVector = Icons.Filled.Home,
                contentDescription = "Home Button",
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(universalPadding)
            )
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

//Image(bitmap = yourBitMap)
fun imageBitmapFromBytes(encodedImageData: ByteArray): ImageBitmap {
    return BitmapFactory.decodeByteArray(encodedImageData, 0, encodedImageData.size).asImageBitmap()
}

