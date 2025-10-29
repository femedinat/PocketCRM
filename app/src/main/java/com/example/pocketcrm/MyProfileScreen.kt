package com.example.pocketcrm

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.pocketcrm.ui.theme.PocketCRMTheme
import androidx.compose.foundation.layout.Arrangement // Import for Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Switch
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyProfileScreen(navController: NavController? = null, userType: String) {
    val currentProfile = if (userType == "Cliente") pauloProfile else myProfile
    var receiveCampaigns by remember { mutableStateOf(true) }
    var receiveNotifications by remember { mutableStateOf(true) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Perfil") },
                navigationIcon = {
                    IconButton(onClick = { navController?.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF007BFF),
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White
                )
            )
        },
        bottomBar = {
            BottomAppBar {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    IconButton(onClick = { navController?.navigate("chats/${userType}") }) {
                        Icon(Icons.Filled.Email, contentDescription = "Chats")
                    }
                    IconButton(onClick = { navController?.navigate("my-profile/${userType}") }) {
                        Icon(Icons.Filled.Person, contentDescription = "My Profile")
                    }
                    if (userType == "Operador") {
                        IconButton(onClick = { navController?.navigate("crm/${userType}") }) {
                            Text("CRM")
                        }
                    }
                }
            }
        }
    ) { paddingValues ->
        val scrollState = rememberScrollState()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = currentProfile.avatar),
                contentDescription = "User Avatar",
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = currentProfile.name,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )
            currentProfile.company?.let { company ->
                Text(
                    text = company,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray
                )
            }
            Spacer(modifier = Modifier.height(24.dp))

            InfoCard(icon = Icons.Filled.Email, text = currentProfile.email)
            Spacer(modifier = Modifier.height(8.dp))
            InfoCard(icon = Icons.Filled.Phone, text = currentProfile.phone)

            Spacer(modifier = Modifier.height(24.dp))

            Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {
                Text(text = "Receber campanhas", style = MaterialTheme.typography.bodyLarge)
                Switch(checked = receiveCampaigns, onCheckedChange = { receiveCampaigns = it })
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {
                Text(text = "Notificação de mensagem", style = MaterialTheme.typography.bodyLarge)
                Switch(checked = receiveNotifications, onCheckedChange = { receiveNotifications = it })
            }

            if (userType == "Cliente") {
                Spacer(modifier = Modifier.height(24.dp))
                Text(
                    text = "Campanhas recebidas",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color(0xFF007BFF),
                    modifier = Modifier.align(Alignment.Start)
                )
                Spacer(modifier = Modifier.height(8.dp))
                // You would typically have a LazyColumn or similar here for a list of campaigns
                // For now, I'll add placeholder text based on your image.
                Text("Acabe com o problema de estocagem!", style = MaterialTheme.typography.bodyMedium)
                Text("Saiba mais", color = Color.Blue)
                Spacer(modifier = Modifier.height(8.dp))
                Text("Conheça as tendências de 2026!", style = MaterialTheme.typography.bodyMedium)
                Text("Saiba mais", color = Color.Blue)
            } else {
                Spacer(modifier = Modifier.height(24.dp))
                Text(
                    text = "Anotações",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color(0xFF007BFF),
                    modifier = Modifier.align(Alignment.Start)
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = "", // This would come from a ViewModel in a real app
                    onValueChange = {},
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color(0xFFF0F8FF),
                        unfocusedContainerColor = Color(0xFFF0F8FF),
                        disabledContainerColor = Color(0xFFF0F8FF),
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent
                    ),
                    shape = RoundedCornerShape(8.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MyProfileScreenPreview() {
    PocketCRMTheme {
        MyProfileScreen(userType = "Operador")
    }
}
