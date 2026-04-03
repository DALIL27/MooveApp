package com.example.mooveapp

import CarteCours
import androidx.compose.runtime.collectAsState
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mooveapp.ui.theme.MooveAppTheme
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import com.example.mooveapp.model.Cours
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.example.mooveapp.ui.CoursViewModel
import com.example.mooveapp.R
import com.example.mooveapp.ui.DetailCoursScreen
import com.example.mooveapp.ui.EditCoursScreen

enum class MooveScreen {
    Start,
    AfficherCours,
    Login,
    ProfilAdherent,

    MesInscriptions,

    ProfilProfesseur,

    MesCoursProf,

    MesActivitesProf,

    DetailCours
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MooveAppTheme {
                MooveApp()
            }
        }
    }
}

@Composable
fun MooveApp() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = MooveScreen.Start.name,
        modifier = Modifier
    ) {
        composable(route = MooveScreen.Start.name) {
            PageAccueil(
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.Center),
                navController = navController
            )
        }

        composable(route = MooveScreen.Login.name) {
            FormulaireLogin(
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.Center),
                navController = navController
            )
        }

        composable(route = MooveScreen.AfficherCours.name) {
            ListeCours(
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.Center),
                navController = navController
            )
        }

        composable(route = MooveScreen.ProfilAdherent.name) {
            ProfilAdherentScreen(
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.Center),
                navController = navController
            )
        }

        composable(route = MooveScreen.MesInscriptions.name) {
            MesInscriptionsScreen(
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.Center),
                navController = navController
            )
        }

        composable(route = MooveScreen.ProfilProfesseur.name) {
            ProfilProfesseurScreen(
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.Center),
                navController = navController
            )
        }

        composable(route = MooveScreen.MesCoursProf.name) {
            MesCoursProfScreen(
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.Center),
                navController = navController
            )
        }

        composable(route = MooveScreen.MesActivitesProf.name) {
            MesActivitesProfScreen(
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.Center),
                navController = navController
            )
        }

        composable(route = "detailCours/{id}") { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id")?.toLong() ?: 0L
            DetailCoursScreen(id = id, navController = navController)
        }

        composable(route = "editCours/{id}") { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id")?.toLong() ?: 0L
            EditCoursScreen(id = id, navController = navController)
        }
    }
}

@Composable
fun PageAccueil(
    modifier: Modifier = Modifier,
    navController: NavHostController= rememberNavController()
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo93moove),
            contentDescription = "Logo 93Moove",
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Bienvenue sur 93Moove")

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                navController.navigate(MooveScreen.AfficherCours.name)
            }
        ) {
            Text(text = "Consulter les cours")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                navController.navigate(MooveScreen.Login.name)
            }
        ) {
            Text(text = "Se connecter")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                navController.navigate(MooveScreen.ProfilAdherent.name)
            }
        ) {
            Text(text = "Profil adhérent")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                navController.navigate(MooveScreen.ProfilProfesseur.name)
            }
        ) {
            Text(text = "Profil professeur")
        }
    }
}

@Composable
fun FormulaireLogin(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    var login by remember { mutableStateOf("") }
    var motDePasse by remember { mutableStateOf("") }

    Column(
        modifier = modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Connexion")

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = login,
            onValueChange = { login = it },
            label = { Text("Login") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = motDePasse,
            onValueChange = { motDePasse = it },
            label = { Text("Mot de passe") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { }) {
            Text(text = "Valider")
        }
    }
}

@Composable
fun ListeCours(
    modifier: Modifier = Modifier,
    coursViewModel: CoursViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    navController: NavHostController
) {
    val listeCours by coursViewModel.coursUi.collectAsState()

    LazyColumn(modifier = modifier.fillMaxSize()) {
        items(listeCours) { cours ->
            CarteCours(cours = cours, onClick = {
                navController.navigate("detailCours/${cours.id}")
            })
        }
    }
}

@Composable
fun ProfilAdherentScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    Column(
        modifier = modifier.padding(16.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Mon profil")

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Nom : Dupont")
        Text(text = "Prénom : Lina")
        Text(text = "Date de naissance : 12/05/2008")
        Text(text = "Email : lina.dupont@mail.com")
        Text(text = "Téléphone : 06 12 34 56 78")
        Text(text = "Quotient familial : 450")

        Spacer(modifier = Modifier.height(24.dp))

        Button(onClick = { }) {
            Text(text = "Modifier mon profil")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                navController.navigate(MooveScreen.MesInscriptions.name)
            }
        ) {
            Text(text = "Mes inscriptions")
        }
    }
}

@Composable
fun MesInscriptionsScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    Column(
        modifier = modifier.padding(16.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Mes inscriptions")

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Type : Activités")
        Text(text = "Année : 2025")

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "- Basket : validée")
        Text(text = "- Natation : en attente")
        Text(text = "- Cinéma : validée")
    }
}

@Composable
fun ProfilProfesseurScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    Column(
        modifier = modifier.padding(16.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Mon profil professeur")

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Nom : Martin")
        Text(text = "Prénom : Sarah")
        Text(text = "Poste : Professeure de danse")
        Text(text = "Date de naissance : 08/09/1990")
        Text(text = "Email : sarah.martin@mail.com")
        Text(text = "Téléphone : 06 98 76 54 32")

        Spacer(modifier = Modifier.height(24.dp))

        Button(onClick = { }) {
            Text(text = "Modifier mon profil")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                navController.navigate(MooveScreen.MesCoursProf.name)
            }
        ) {
            Text(text = "Mes cours")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                navController.navigate(MooveScreen.MesActivitesProf.name)
            }
        ) {
            Text(text = "Mes activités")
        }
    }
}

@Composable
fun MesCoursProfScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    Column(
        modifier = modifier.padding(16.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Mes cours")

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "- Danse moderne : lundi 18h")
        Text(text = "- Hip-hop : mercredi 16h")
        Text(text = "- Danse enfants : vendredi 17h")
    }
}

@Composable
fun MesActivitesProfScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    Column(
        modifier = modifier.padding(16.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Mes activités")

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "- Sortie musée : mardi 14h")
        Text(text = "- Atelier cinéma : jeudi 15h")
        Text(text = "- Visite du zoo : samedi 10h")
    }
}

fun imagePourCours(nom: String): Int {
    return when (nom.lowercase()) {
        "baseball", "baseball avancé" -> R.drawable.baseball
        "basket" -> R.drawable.basket
        "cinéma", "cinema" -> R.drawable.cinema
        "football", "foot" -> R.drawable.foot
        "musée", "musee" -> R.drawable.musee
        "natation" -> R.drawable.natation
        "tennis" -> R.drawable.tennis
        "dance", "danse" -> R.drawable.dance
        "zoo" -> R.drawable.zoo
        else -> R.drawable.logo93moove // image par défaut
    }
}

@Preview(showBackground = true)
@Composable
fun PageAccueilPreview() {
    MooveAppTheme {
        PageAccueil(
            modifier = Modifier.fillMaxSize(),
            navController = rememberNavController()
        )
    }
}