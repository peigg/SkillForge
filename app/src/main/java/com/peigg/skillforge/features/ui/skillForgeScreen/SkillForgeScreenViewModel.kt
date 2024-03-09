package com.peigg.skillforge.features.ui.skillForgeScreen
import android.util.Log
import com.peigg.skillforge.data.bd.repositories.SkillForgeRepository
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.peigg.skillforge.domain.Coaches
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject



@HiltViewModel
class SkillForgeScreenViewModel @Inject constructor(
    private val skillForgeRepository: SkillForgeRepository
) : ViewModel() {
    private val _coaches = MutableStateFlow<List<Coaches>>(emptyList())
    val coaches: StateFlow<List<Coaches>> = _coaches

    init {
        viewModelScope.launch {
            try {
                Log.d("SkillForgeScreenViewModel", "Setting up database")
                skillForgeRepository.setupSkillForgeDatabase()
                Log.d("SkillForgeScreenViewModel", "Fetching coaches")
                _coaches.value = skillForgeRepository.getCoaches()
                Log.d("SkillForgeScreenViewModel", "Coaches fetched successfully: ${_coaches.value}")
            } catch (e: Exception) {
                Log.e("SkillForgeScreenViewModel", "Error initializing ViewModel", e)
            }
        }
    }
}
/*
@HiltViewModel
class SkillForgeScreenViewModel @Inject constructor() : ViewModel()

    private val _personas = mutableStateOf(listOf<Persona>())
    val personas: State<List<Persona>> = _personas


    init {
        viewModelScope.launch {


            _personas.value = listOf(

                Persona(
                    "Juan Pérez",
                    R.drawable.image_juan,
                    "Desarrollo de aplicaciones móviles",
                    "Soy un desarrollador de aplicaciones móviles con 5 años de experiencia. He trabajado en proyectos de gran envergadura y me encantaría ayudarte a mejorar tus habilidades.",
                    "S/ 100"

                ),
                Persona(
                    "Paco Ramirez",
                    R.drawable.image_paco,
                    "Desarrollo web",
                    "Soy una desarrollador web con 3 años de experiencia. He trabajado" +
                            " en proyectos de gran envergadura y me encantaría ayudarte a mejorar tus habilidades.",
                    "S/ 80"
                ),
                Persona(
                    "Carla Sánchez",
                    R.drawable.imagen_carla,
                    "Desarrollo .NET",
                    "Soy una desarrollador fullstack con 15 años de experiencia. He trabajado en proyectos de gran envergadura y me encantaría ayudarte a mejorar tus habilidades.",
                    "S/ 120"
                )
            )
        }

 */


