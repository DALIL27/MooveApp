package com.example.mooveapp.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController

@Composable
fun EditCoursScreen(
    id: Long,
    navController: NavHostController,
    coursViewModel: CoursViewModel = viewModel()
) {
    val listeCours by coursViewModel.coursUi.collectAsState()
    val cours = listeCours.firstOrNull { it.id == id }

    if (cours == null) {
        Text("Cours introuvable")
        return
    }

    var nom by remember { mutableStateOf(cours.nom) }
    var description by remember { mutableStateOf(cours.description) }
    var prixText by remember { mutableStateOf(cours.prix.toString()) }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Modifier le cours")

        Spacer(Modifier.height(16.dp))

        OutlinedTextField(
            value = nom,
            onValueChange = { nom = it },
            label = { Text("Nom") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(8.dp))

        OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Description") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(8.dp))

        OutlinedTextField(
            value = prixText,
            onValueChange = { prixText = it },
            label = { Text("Prix") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(16.dp))

        Button(onClick = {
            val prix = prixText.toDoubleOrNull() ?: cours.prix
            coursViewModel.updateCours(
                cours.copy(
                    nom = nom,
                    description = description,
                    prix = prix
                )
            )
            navController.popBackStack()
        }) {
            Text("Enregistrer")
        }

        Spacer(Modifier.height(8.dp))

        Button(onClick = { navController.popBackStack() }) {
            Text("Annuler")
        }
    }
}