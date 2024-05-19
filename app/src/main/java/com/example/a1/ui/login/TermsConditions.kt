package com.example.a1.ui.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.a1.ui.fastFeast.Footer

@Composable
fun TermsAndConditions(onBtnClick: () -> Unit) {
    val startColor = Color(0xFFFF9D7E)
    val endColor = Color(0xFF975743)
    //Screen settings
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    var HeaderBarHeight = (screenHeight*7/100)

    Column {
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
        ) {}

        LazyColumn (modifier= Modifier.padding(8.dp)){
            item {
                Text(
                    text = "Terms and Conditions",
                    style = MaterialTheme.typography.headlineLarge,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                Text(
                    text = "Effective Date: 18 May 2024",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                SectionTitle("1. Introduction")
                ParagraphText(
                    "Welcome to FastFeasts. These Terms and Conditions govern your use of our fast food ordering application, including dine-in and takeaway services, customizable burgers, and other items (collectively, the \"Services\"). By using our Services, you agree to be bound by these Terms."
                )

                SectionTitle("2. User Accounts")
                ParagraphText(
                    "To use our Services, you may be required to create an account. When creating an account, you must provide accurate and complete information, including your username, birthday, phone number, email, and credit card information. You are responsible for maintaining the confidentiality of your account information and for all activities that occur under your account."
                )

                SectionTitle("3. Ordering and Payment")
                ParagraphText(
                    "You can place orders for dine-in or takeaway through our app. All orders are subject to acceptance by us. Prices are subject to change without notice. Payment must be made at the time of ordering, and you authorize us to charge your credit card for the total amount of your order."
                )

                SectionTitle("4. Customizable Burgers and Other Items")
                ParagraphText(
                    "Our app allows you to customize your burgers and other items. While we strive to accommodate your preferences, we do not guarantee that all customizations will be available or possible. We reserve the right to refuse any customizations that are not feasible or that compromise the quality of our products."
                )

                SectionTitle("5. Privacy")
                ParagraphText(
                    "Your use of our Services is also governed by our Privacy Policy, which explains how we collect, use, and disclose information about you. By using our Services, you consent to the collection and use of your information as described in our Privacy Policy."
                )

                SectionTitle("6. Intellectual Property")
                ParagraphText(
                    "All content and materials available on our app, including but not limited to text, graphics, logos, and software, are the property of [Your Company Name] or its licensors and are protected by intellectual property laws. You may not use, reproduce, or distribute any content from our app without our prior written permission."
                )

                SectionTitle("7. Limitation of Liability")
                ParagraphText(
                    "To the fullest extent permitted by law, [Your Company Name] shall not be liable for any indirect, incidental, special, consequential, or punitive damages, or any loss of profits or revenues, whether incurred directly or indirectly, or any loss of data, use, goodwill, or other intangible losses, resulting from (i) your use of or inability to use the Services; (ii) any unauthorized access to or use of our servers and/or any personal information stored therein."
                )

                SectionTitle("8. Changes to the Terms")
                ParagraphText(
                    "We may update these Terms from time to time. We will notify you of any changes by posting the new Terms on this page and updating the \"Effective Date\" at the top. You are advised to review these Terms periodically for any changes."
                )

                SectionTitle("9. Governing Law")
                ParagraphText(
                    "These Terms shall be governed and construed in accordance with the laws of [Your Jurisdiction], without regard to its conflict of law provisions. Any dispute arising out of or in connection with these Terms shall be subject to the exclusive jurisdiction of the courts of [Your Jurisdiction]."
                )

                Button(
                    onClick = onBtnClick,
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    colors = ButtonDefaults.buttonColors(Color(0xFFFF9D7E))
                ) {
                    Text("Finish", color = Color.White)
                }

                Footer()
            }
        }
    }
}
