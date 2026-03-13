package com.example.mooveapp.ui

import com.example.mooveapp.model.Cours

data class CoursUiState(
    val listeCours: List<Cours> = emptyList()
)