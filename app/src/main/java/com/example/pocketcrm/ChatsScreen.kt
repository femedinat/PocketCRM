package com.example.pocketcrm

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Badge
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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


@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun ChatsScreen(navController: NavController? = null) {
    var chatList by remember { mutableStateOf(chats) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Chats") },
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
                    IconButton(onClick = { navController?.navigate("chats") }) {
                        Icon(Icons.Filled.Email, contentDescription = "Chats")
                    }
                    IconButton(onClick = { navController?.navigate("my-profile") }) {
                        Icon(Icons.Filled.Person, contentDescription = "My Profile")
                    }
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
            LazyColumn {
                items(chatList) { chat ->
                    ChatItem(
                        chat = chat,
                        onClick = { navController?.navigate("chat/${chat.userId}") },
                        onLongClick = {
                            val updatedChats = chatList.map {
                                if (it.userId == chat.userId) {
                                    it.copy(isVip = !it.isVip)
                                } else {
                                    it
                                }
                            }
                            chatList = updatedChats
                        }
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ChatItem(chat: Chat, onClick: () -> Unit, onLongClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .combinedClickable(onClick = onClick, onLongClick = onLongClick)
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = chat.avatar),
            contentDescription = "User Avatar",
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column(modifier = Modifier.weight(1f)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = chat.name, style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.Bold)
                    if (chat.isVip) {
                        Spacer(modifier = Modifier.width(4.dp))
                        Badge(containerColor = Color.Red, contentColor = Color.White) { Text("VIP") }
                    }
                }
                Text(text = chat.lastMessage, style = MaterialTheme.typography.bodyMedium)
            }
            Text(text = chat.time, style = MaterialTheme.typography.bodySmall)
            if (chat.unreadCount > 0) {
                Spacer(modifier = Modifier.width(8.dp))
                Badge { Text(text = chat.unreadCount.toString()) }
            }
        }
}

@Preview(showBackground = true)
@Composable
fun ChatsScreenPreview() {
    PocketCRMTheme {
        ChatsScreen()
    }
}
