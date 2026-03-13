package com.example.mooveapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mooveapp.model.Cours
import com.example.mooveapp.service.CoursApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CoursViewModel : ViewModel() {

    private val _coursUi = MutableStateFlow<List<Cours>>(emptyList())
    val coursUi: StateFlow<List<Cours>> = _coursUi

    init {
        getCours()
    }

    fun getCours() {
        viewModelScope.launch {
            val response = CoursApi.retrofitService.getCoursList()

            val coursAvecImages = response.map { cours ->
                cours.copy(imageRes = imageResPourCours(cours.nom))
            }
            _coursUi.value = coursAvecImages
        }
    }

    fun deleteCours(id: Long) {
        viewModelScope.launch {
            CoursApi.retrofitService.deleteCours(id)
            // on recharge la liste après suppression
            getCours()
        }
    }

    fun updateCours(cours: Cours) {
        viewModelScope.launch {
            CoursApi.retrofitService.updateCours(cours.id, cours)
            getCours() // refresh la liste
        }
    }

    fun addCours(cours: Cours) {
        viewModelScope.launch {
            CoursApi.retrofitService.addCours(cours)
            getCours()
        }
    }
}