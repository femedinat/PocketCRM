package com.example.pocketcrm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pocketcrm.ui.theme.PocketCRMTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PocketCRMTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "login") {
                    composable("login") { LoginScreen(navController) }
                    composable("chats") { ChatsScreen(navController) }
                    composable("chat/{userId}") { backStackEntry ->
                        val userId = backStackEntry.arguments?.getString("userId")
                        ChatScreen(navController, userId)
                    }
                    composable("profile/{userId}") { backStackEntry ->
                        val userId = backStackEntry.arguments?.getString("userId")
                        ProfileScreen(navController, userId)
                    }
                    composable("my-profile") { MyProfileScreen(navController) }
                }
            }
        }
    }
}