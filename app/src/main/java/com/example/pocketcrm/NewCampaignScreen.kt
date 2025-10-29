package com.example.pocketcrm

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.pocketcrm.ui.theme.PocketCRMTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewCampaignScreen(navController: NavController? = null) {
    var campaignTitle by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("") }
    var imageName by remember { mutableStateOf("") }
    var hasActionButton by remember { mutableStateOf(false) }
    var buttonText by remember { mutableStateOf("") }
    var buttonUrl by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Nova campanha") },
                navigationIcon = {
                    IconButton(onClick = { navController?.popBackStack() }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF007BFF),
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .fillMaxSize()
        ) {
            Text("Título da campanha")
            OutlinedTextField(
                value = campaignTitle,
                onValueChange = { campaignTitle = it },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))

            Text("Mensagem")
            OutlinedTextField(
                value = message,
                onValueChange = { message = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))

            Text("Adicionar imagem")
            OutlinedTextField(
                value = imageName,
                onValueChange = { imageName = it },
                trailingIcon = {
                    Icon(Icons.Default.AddCircle, contentDescription = "Add Image")
                },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Botão de ação")
                Switch(
                    checked = hasActionButton,
                    onCheckedChange = { hasActionButton = it }
                )
            }

            if (hasActionButton) {
                Spacer(modifier = Modifier.height(16.dp))
                Text("Texto do botão")
                OutlinedTextField(
                    value = buttonText,
                    onValueChange = { buttonText = it },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text("URL botão")
                OutlinedTextField(
                    value = buttonUrl,
                    onValueChange = { buttonUrl = it },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NewCampaignScreenPreview() {
    PocketCRMTheme {
        NewCampaignScreen()
    }
}