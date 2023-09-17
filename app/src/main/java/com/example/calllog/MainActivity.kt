package com.example.calllog

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.res.painterResource

data class CallLog(val contactName: String, val phoneNumber: String, val date: String)

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun CallLogScreen() {
    var callLogs by remember { mutableStateOf<List<CallLog>>(emptyList()) }
    var lastSyncTime by remember { mutableStateOf("Last Sync: 10 minutes ago") }
    var searchText by remember { mutableStateOf("") }

    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current

    // Simulated data for call logs
    callLogs = listOf(
        CallLog("John Doe", "123-456-7890", "2 hours ago"),
        CallLog("Jane Smith", "987-654-3210", "4 hours ago"),
        // Add more call logs here
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White) // Set the background color to white
    ) {
        // Header with settings and refresh icons on the right
        TopAppBar(
            title = {
                Text(
                    text = "Call Logs",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                )
            },
            actions = {
                IconButton(
                    onClick = {
                        // Add logic for settings here
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Settings,
                        contentDescription = "Settings",
                    )
                }
                IconButton(
                    onClick = {
                        // Add logic for refresh here
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Refresh,
                        contentDescription = "Refresh",
                    )
                }
            },
            modifier = Modifier.fillMaxWidth()
        )

        Text(
            text = lastSyncTime,
            fontSize = 12.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, bottom = 8.dp)
        )

        // Call logs list
        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {
            items(callLogs) { callLog ->
                CallLogCard(callLog)
                Divider(modifier = Modifier.padding(horizontal = 16.dp))
            }
        }

        // Upload button
        Button(
            onClick = {
                // Add logic for uploading call logs here
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .background(Color.Red),
        ) {
            Text(text = "Upload", color = Color.White)
        }
    }
}



@Composable
fun CallLogCard(callLog: CallLog) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = "Contact: ${callLog.contactName}", fontWeight = FontWeight.Bold)
            Text(text = "Phone Number: ${callLog.phoneNumber}")
            Text(text = "Time: ${callLog.date}")

            // Separator line
            Spacer(modifier = Modifier.height(8.dp))
            Divider(modifier = Modifier.fillMaxWidth())

            // Microphone icon and "Play Recording" button
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Custom microphone icon on the left
                Image(
                    painter = painterResource(id = R.drawable.ic), // Replace with your custom microphone icon resource
                    contentDescription = "Microphone",
                    modifier = Modifier.size(24.dp)
                )
                Text(text = "Contact Info")

                // "Play Recording" button on the right
                Icon(
                    imageVector = Icons.Default.PlayArrow,
                    contentDescription = "Play Recording",
                    tint = Color.Green,
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
}


@Preview
@Composable
fun CallLogScreenPreview() {
    CallLogScreen()
}
