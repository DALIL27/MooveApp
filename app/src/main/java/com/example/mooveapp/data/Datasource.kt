package com.example.mooveapp.data

import com.example.mooveapp.R
import com.example.mooveapp.model.Cours

object Datasource {
    fun loadCours(): List<Cours> {
        return listOf(
            Cours(
                id = 1,
                nom = "Baseball",
                description = "Cours de baseball pour enfants et adultes",
                prix = 120.0,
                imageRes = R.drawable.baseball
            ),
            Cours(
                id = 2,
                nom = "Basket",
                description = "Cours de basket niveau débutant",
                prix = 100.0,
                imageRes = R.drawable.basket
            ),
            Cours(
                id = 3,
                nom = "Cinéma",
                description = "Atelier découverte du cinéma et de l’image",
                prix = 95.0,
                imageRes = R.drawable.cinema
            ),
            Cours(
                id = 4,
                nom = "Football",
                description = "Cours de football pour adolescents",
                prix = 110.0,
                imageRes = R.drawable.foot
            ),
            Cours(
                id = 5,
                nom = "Musée",
                description = "Activité culturelle autour des musées",
                prix = 80.0,
                imageRes = R.drawable.musee
            ),
            Cours(
                id = 6,
                nom = "Natation",
                description = "Cours de natation pour tous niveaux",
                prix = 90.0,
                imageRes = R.drawable.natation
            ),
            Cours(
                id = 7,
                nom = "Tennis",
                description = "Cours de tennis pour adolescents",
                prix = 110.0,
                imageRes = R.drawable.tennis
            ),
            Cours(
                id = 8,
                nom = "Zoo",
                description = "Sortie éducative et découverte du monde animal",
                prix = 85.0,
                imageRes = R.drawable.zoo
            )
        )
    }
}