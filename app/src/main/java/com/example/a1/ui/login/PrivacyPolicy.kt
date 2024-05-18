package com.example.a1.ui.login

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.a1.ui.fastFeast.BackButton
import com.example.a1.ui.fastFeast.Footer

@Composable
fun PrivacyPolicy(onBtnClick: () -> Unit) {
    Column {
        LazyColumn (modifier= Modifier.padding(8.dp)) {
            item {
                Text(
                    text = "Privacy Policy",
                    style = MaterialTheme.typography.headlineLarge,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                Text(
                    text = "Effective Date: 18 May 2024",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                Text(
                    text = "Welcome to FastFeasts. We are committed to protecting your privacy. This Privacy Policy explains how we collect, use, and disclose information about you when you use our application and services (collectively, the \"Services\"). By using our Services, you agree to the collection and use of information in accordance with this policy.",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                SectionTitle("Information We Collect")
                SectionText("1. Personal Information")
                ParagraphText(
                    "- Account Information: When you create an account, we may collect information such as your name, email address, phone number, and password.\n- Profile Information: You may choose to provide additional information for your profile, such as a profile picture, bio, and other details."
                )

                SectionText("2. Usage Information")
                ParagraphText(
                    "- Log Data: We automatically collect information about your interactions with our Services, such as the pages or content you view, the date and time of your visit, and other usage details.\n- Device Information: We collect information about the device you use to access our Services, including the hardware model, operating system, unique device identifiers, and mobile network information."
                )

                SectionTitle("How We Use Your Information")
                ParagraphText(
                    "We use the information we collect to:\n- Provide, operate, and maintain our Services.\n- Improve, personalize, and expand our Services.\n- Communicate with you, including for customer service, support, and administrative purposes.\n- Process transactions and send you related information, including purchase confirmations and invoices.\n- Monitor and analyze trends, usage, and activities in connection with our Services.\n- Detect, prevent, and address technical issues and fraudulent activity."
                )

                SectionTitle("Sharing Your Information")
                ParagraphText(
                    "We may share your information in the following ways:\n- Service Providers: We may share your information with third-party service providers who perform services on our behalf, such as payment processing, data analysis, email delivery, and hosting services.\n- Legal Requirements: We may disclose your information if required to do so by law or in response to valid requests by public authorities (e.g., a court or a government agency).\n- Business Transfers: If we are involved in a merger, acquisition, or asset sale, your information may be transferred as part of that transaction."
                )

                SectionTitle("Security of Your Information")
                ParagraphText(
                    "We take the security of your information seriously and use reasonable measures to protect it. However, no method of transmission over the internet or method of electronic storage is completely secure, and we cannot guarantee absolute security."
                )

                SectionTitle("Your Choices and Rights")
                ParagraphText(
                    "You have the following rights regarding your personal information:\n- Access and Update: You can access and update your personal information through your account settings.\n- Delete: You can request the deletion of your personal information by contacting us at [contact email].\n- Opt-Out: You can opt-out of receiving promotional communications from us by following the unsubscribe instructions in those communications."
                )

                SectionTitle("Changes to This Privacy Policy")
                ParagraphText(
                    "We may update this Privacy Policy from time to time. We will notify you of any changes by posting the new Privacy Policy on this page and updating the \"Effective Date\" at the top. You are advised to review this Privacy Policy periodically for any changes."
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

@Composable
fun SectionTitle(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.headlineMedium.copy(fontSize = 18.sp),
        modifier = Modifier.padding(vertical = 8.dp)
    )
}

@Composable
fun SectionText(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.headlineMedium,
        modifier = Modifier.padding(bottom = 8.dp)
    )
}

@Composable
fun ParagraphText(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier.padding(bottom = 16.dp)
    )
}
