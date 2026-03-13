package com.example.mooveapp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.compose.runtime.collectAsState

@Composable
fun DetailCoursScreen(
    id: Long,
    navController: NavHostController,
    coursViewModel: CoursViewModel = viewModel()
) {
    val listeCours by coursViewModel.coursUi.collectAsState()

    val cours = listeCours.firstOrNull { it.id == id }

    Column(modifier = Modifier.padding(16.dp)) {
        if (cours == null) {
            Text("Cours introuvable")
        } else {
            Image(
                painter = painterResource(id = cours.imageRes),
                contentDescription = cours.nom,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp),
                contentScale = ContentScale.Crop
            )

            Spacer(Modifier.height(16.dp))
            Text(text = cours.nom, style = MaterialTheme.typography.titleLarge)
            Spacer(Modifier.height(8.dp))
            Text(text = cours.description)
            Spacer(Modifier.height(8.dp))
            Text(text = "Prix : ${cours.prix} €")

            Spacer(Modifier.height(16.dp))

            Button(onClick = {
                coursViewModel.deleteCours(id)
                navController.popBackStack()
            }) {
                Text("Supprimer")
            }

            Button(onClick = { navController.navigate("editCours/$id") }) {
                Text("Modifier")
            }

            Spacer(Modifier.height(24.dp))
            Button(onClick = { navController.popBackStack() }) {
                Text("Retour")
            }
        }
    }
}