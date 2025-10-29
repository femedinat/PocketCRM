package com.example.pocketcrm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pocketcrm.ui.theme.PocketCRMTheme
import androidx.navigation.NavType
import androidx.navigation.navArgument

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PocketCRMTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "login") {
                    composable("login") { LoginScreen(navController) }
                    composable("main_flow/{userType}", arguments = listOf(navArgument("userType") { type = NavType.StringType })) {
                        val userType = it.arguments?.getString("userType") ?: "Cliente"
                        if (userType == "Cliente") {
                            ChatsScreen(navController, userType)
                        } else {
                            CRMScreen(navController, userType)
                        }
                    }
                    composable("chats/{userType}", arguments = listOf(navArgument("userType") { type = NavType.StringType })) { backStackEntry ->
                        val userType = backStackEntry.arguments?.getString("userType") ?: "Cliente"
                        ChatsScreen(navController, userType)
                    }
                    composable("chat/{userId}/{userType}", arguments = listOf(navArgument("userId") { type = NavType.StringType }, navArgument("userType") { type = NavType.StringType })) { backStackEntry ->
                        val userId = backStackEntry.arguments?.getString("userId")
                        val userType = backStackEntry.arguments?.getString("userType") ?: "Cliente"
                        ChatScreen(navController, userId, userType)
                    }
                    composable("profile/{userId}") { backStackEntry ->
                        val userId = backStackEntry.arguments?.getString("userId")
                        ProfileScreen(navController, userId)
                    }
                    composable("my-profile/{userType}", arguments = listOf(navArgument("userType") { type = NavType.StringType })) { backStackEntry ->
                        val userType = backStackEntry.arguments?.getString("userType") ?: "Cliente"
                        MyProfileScreen(navController, userType)
                    }
                    composable("crm/{userType}", arguments = listOf(navArgument("userType") { type = NavType.StringType })) { backStackEntry ->
                        val userType = backStackEntry.arguments?.getString("userType") ?: "Operador"
                        CRMScreen(navController, userType)
                    }
                    composable("new-campaign") { NewCampaignScreen(navController) }
                }
            }
        }
    }
}