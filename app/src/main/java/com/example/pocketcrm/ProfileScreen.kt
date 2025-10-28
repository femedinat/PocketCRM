package com.example.pocketcrm

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Badge
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
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
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.pocketcrm.ui.theme.PocketCRMTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(navController: NavController? = null, userId: String?) {
    val user = users.find { it.id == userId }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Cliente") },
                navigationIcon = {
                    IconButton(onClick = { navController?.popBackStack() }) { // Navigate back
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    if (user?.isVip == true) {
                        Badge(containerColor = Color.Red, contentColor = Color.White) { Text("VIP") }
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
        if (user != null) {
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
                    painter = painterResource(id = user.avatar),
                    contentDescription = "User Avatar",
                    modifier = Modifier
                        .size(120.dp)
                        .clip(CircleShape)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = user.name, style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
                Text(text = user.company, style = MaterialTheme.typography.bodyMedium)

                Spacer(modifier = Modifier.height(16.dp))

                InfoCard(icon = Icons.Filled.Email, text = user.email)
                Spacer(modifier = Modifier.height(8.dp))
                InfoCard(icon = Icons.Filled.Phone, text = user.phone)

                Spacer(modifier = Modifier.height(16.dp))

                StatusItem(status = user.status)

                Spacer(modifier = Modifier.height(16.dp))

                ReminderCard(reminder = user.reminder)

                Spacer(modifier = Modifier.height(16.dp))

                QuickNoteCard(note = user.quickNote)

                Spacer(modifier = Modifier.height(16.dp))

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
        } else {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("Usuário não encontrado.")
            }
        }
    }
}

@Composable
fun StatusItem(status: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(text = "Status:", style = MaterialTheme.typography.bodyLarge, color = Color(0xFF007BFF))
        Spacer(modifier = Modifier.width(8.dp))
        Badge(containerColor = Color.Red, contentColor = Color.White) {
            Text(text = status)
        }
    }
}

@Composable
fun ReminderCard(reminder: String) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        color = Color(0xFFF0F8FF)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Filled.CalendarToday,
                    contentDescription = null,
                    tint = Color.Gray,
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text(text = "Lembrete de contato:", fontSize = 12.sp, color = Color.Gray)
                    Text(text = reminder, fontSize = 16.sp, color = Color.Black)
                }
            }
            IconButton(onClick = { /*TODO*/ }) {
                Icon(Icons.Filled.Edit, contentDescription = "Edit Reminder")
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    PocketCRMTheme {
        ProfileScreen(userId = "paulo")
    }
}
