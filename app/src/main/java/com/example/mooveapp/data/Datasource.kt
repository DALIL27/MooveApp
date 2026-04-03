package com.example.mooveapp.data

import com.example.mooveapp.R
import com.example.mooveapp.model.Cours

object Datasource {
    fun loadCours(): List<Cours> {
        return listOf(
            Cours(
                id = 1,
                nom = "Natation",
                description = "Cours de natation pour tous niveaux",
                prix = 50.0,
                imageRes = R.drawable.natation
            ),
            Cours(
                id = 2,
                nom = "Dance",
                description = "Cours de dance pour tous les niveaux",
                prix = 50.0,
                imageRes = R.drawable.dance
            )
        )
    }
}