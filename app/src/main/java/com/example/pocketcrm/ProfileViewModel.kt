package com.example.pocketcrm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProfileViewModel : ViewModel() {

    private val _quickNote = MutableStateFlow("")
    val quickNote: StateFlow<String> = _quickNote

    fun fetchNote(userId: String) {
        viewModelScope.launch {
            try {
                val response = Network.apiService.getNotes(userId)
                // We're using the content of the first note as the quick note
                response.notes.firstOrNull()?.let {
                    _quickNote.value = it.content
                }
            } catch (e: Exception) {
                // Handle error case, e.g., show a message to the user
                _quickNote.value = "Falha ao carregar anotação."
            }
        }
    }
}