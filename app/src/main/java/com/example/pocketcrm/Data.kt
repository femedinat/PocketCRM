package com.example.pocketcrm

import androidx.annotation.DrawableRes

data class Message(val text: String, val time: String, val isFromMe: Boolean)

data class User(
    val id: String,
    val name: String,
    @DrawableRes val avatar: Int,
    val company: String,
    val email: String,
    val phone: String,
    val status: String,
    val quickNote: String,
    val reminder: String,
    var isVip: Boolean = false
)

data class Chat(
    val userId: String,
    val name: String,
    val lastMessage: String,
    val time: String,
    val unreadCount: Int,
    @DrawableRes val avatar: Int,
    val messages: List<Message>,
    var isVip: Boolean = false
)

data class CrmItem(
    val companyName: String,
    val status: String,
    val contactName: String,
    @DrawableRes val contactAvatar: Int,
    val isVip: Boolean = false
)

val pauloMessages = listOf(
    Message("Estamos terminando a proposta. Eu te encaminho até segunda!", "10:44 AM", false),
    Message("Ótimo! Obrigado pelo update.", "10:45 AM", true)
)

val andreMessages = listOf(
    Message("Mensagem de Campanha: Não perca nossos produtos", "10:00 AM", false)
)

val juliaMessages = listOf(
    Message("Pode me passar uma previsão?", "10:00 AM", false)
)

val users = listOf(
    User("paulo", "Paulo Bezerra", R.drawable.paulo, "Empresa 2 SA", "paulo.bezerra@empresa2.com.br", "11 31231-1231", "Em proposta", "Tem um problema claro em gerenciamento de estoque. Maior interesse na ferramenta de gestão de armazenagem.", "24/12/2025 09:00:00", isVip = true),
    User("andre", "André", R.drawable.andre, "Empresa 1 SA", "andre@empresa1.com.br", "11 98765-4321", "Inicial", "", "", isVip = false),
    User("julia", "Júlia", R.drawable.julia, "Empresa 3 SA", "julia@empresa3.com.br", "11 12345-6789", "Sem Resp.", "", "", isVip = false),
    User("jose", "José Silva", R.drawable.jose, "Minha Empresa", "jose.silva@crm.com.br", "11 31231-1231", "", "", "", isVip = false)
)

val chats = listOf(
    Chat("paulo", "Paulo", "Ótimo! Obrigado pelo update.", "10:45 AM", 1, R.drawable.paulo, pauloMessages, isVip = true),
    Chat("andre", "André", "Mensagem de Campanha: Não perca nos...", "10:00 AM", 0, R.drawable.andre, andreMessages),
    Chat("julia", "Júlia", "Pode me passar uma previsão?", "10:00 AM", 0, R.drawable.julia, juliaMessages)
)


val crmItems = listOf(
    CrmItem("Empresa 2 SA", "Em proposta", "Paulo", R.drawable.paulo, isVip = true),
    CrmItem("Empresa 3 SA", "Inicial", "André", R.drawable.andre),
    CrmItem("Empresa 5 SA", "Sem Resp.", "Júlia", R.drawable.julia)
)

// New data class and profile definitions for MyProfileScreen
data class UserProfile(
    @DrawableRes val avatar: Int,
    val name: String,
    val company: String? = null,
    val email: String,
    val phone: String
)

val pauloProfile = UserProfile(
    avatar = R.drawable.paulo,
    name = "Paulo Bezerra",
    company = "Empresa 2 SA",
    email = "paulo.bezerra@empresa2.com.br",
    phone = "11 31231-1231"
)

val myProfileData = users.find { it.id == "jose" }!!
val myProfile = UserProfile(
    avatar = myProfileData.avatar,
    name = myProfileData.name,
    company = myProfileData.company,
    email = myProfileData.email,
    phone = myProfileData.phone
)
