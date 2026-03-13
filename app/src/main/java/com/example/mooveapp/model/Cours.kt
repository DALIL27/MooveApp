package com.example.mooveapp.model

import androidx.annotation.DrawableRes

data class Cours(
    val id: Long,                // Spring envoie Long
    val nom: String,
    val description: String,
    val prix: Double,
    @DrawableRes val imageRes: Int = 0
)