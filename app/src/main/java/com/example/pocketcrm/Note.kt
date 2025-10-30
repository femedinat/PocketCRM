package com.example.pocketcrm

data class Note(
    val id: String,
    val userId: String,
    val content: String,
    val createdAt: String
)

data class NotesResponse(
    val notes: List<Note>
)