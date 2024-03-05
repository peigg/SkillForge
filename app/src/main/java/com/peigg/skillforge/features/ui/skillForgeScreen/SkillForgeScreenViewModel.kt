package com.peigg.skillforge.features.ui.skillForgeScreen
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.lifecycle.viewModelScope
import com.peigg.skillforge.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SkillForgeScreenViewModel @Inject constructor() : ViewModel() {
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
    }
}
