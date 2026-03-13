package com.example.mooveapp.ui

import com.example.mooveapp.R

fun imageResPourCours(nom: String): Int {
    return when (nom.trim().lowercase()) {
        "baseball", "baseball avancé" -> R.drawable.baseball
        "basket" -> R.drawable.basket
        "cinéma", "cinema" -> R.drawable.cinema
        "football", "foot" -> R.drawable.foot
        "musée", "musee" -> R.drawable.musee
        "natation" -> R.drawable.natation
        "tennis" -> R.drawable.tennis
        "zoo" -> R.drawable.zoo
        else -> R.drawable.logo93moove
    }
}