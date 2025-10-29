package com.example.pocketcrm

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CRMScreen(navController: NavController? = null, userType: String) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("CRM") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF007BFF),
                    titleContentColor = Color.White
                )
            )
        },
        bottomBar = {
            BottomAppBar {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    IconButton(onClick = { navController?.navigate("chats/$userType") }) {
                        Icon(Icons.Filled.Email, contentDescription = "Chats")
                    }
                    IconButton(onClick = { navController?.navigate("my-profile/$userType") }) {
                        Icon(Icons.Filled.Person, contentDescription = "My Profile")
                    }
                    if (userType == "Operador") {
                        IconButton(onClick = { navController?.navigate("crm/$userType") }) {
                            Text("CRM")
                        }
                    }
                }
            }
        },
        floatingActionButton = {
            if (userType == "Operador") {
                FloatingActionButton(onClick = { navController?.navigate("new-campaign") }) {
                    Icon(Icons.Filled.Add, contentDescription = "Add")
                }
            }
        }
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            OutlinedTextField(
                value = "",
                onValueChange = {},
                placeholder = { Text("Search") },
                leadingIcon = { Icon(Icons.Filled.Search, contentDescription = null) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )
            LazyColumn(
                modifier = Modifier.padding(horizontal = 8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(crmItems) { item ->
                    CrmListItem(item = item)
                }
            }
        }
    }
}

@Composable
fun CrmListItem(item: CrmItem) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .width(4.dp)
                    .height(50.dp)
                    .background(
                        color = when (item.status) {
                            "Em proposta" -> Color.Red
                            "Inicial" -> Color(0xFFD4AF37)
                            "Sem Resp." -> Color.Gray
                            else -> Color.Transparent
                        }
                    )
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column(modifier = Modifier.weight(1f)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = item.companyName, fontWeight = FontWeight.Bold)
                    if (item.isVip) {
                        Spacer(modifier = Modifier.width(4.dp))
                        Badge(containerColor = Color.Red, contentColor = Color.White) { Text("VIP") }
                    }
                }
                Text(text = item.contactName)
            }
            Column(horizontalAlignment = Alignment.End) {
                Badge(
                    containerColor = when (item.status) {
                        "Em proposta" -> Color.Red
                        "Inicial" -> Color(0xFFD4AF37)
                        "Sem Resp." -> Color.Gray
                        else -> Color.Transparent
                    }
                ) { Text(text = item.status) }
                Spacer(modifier = Modifier.height(4.dp))
                Image(
                    painter = painterResource(id = item.contactAvatar),
                    contentDescription = "Contact Avatar",
                    modifier = Modifier
                        .size(30.dp)
                        .clip(CircleShape)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CRMScreenPreview() {
    PocketCRMTheme {
        CRMScreen(userType = "Operador")
    }
}
