package com.peigg.skillforge.screens.theme
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.peigg.skillforge.R

@Composable
fun SkillForgeScreen(navController: NavController, paddingValues: PaddingValues) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues), // Aplica el padding aquí
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        PersonaList()
    }
}

data class Persona(val name: String, val image: Int)

@Composable
fun PersonaList() {
    val personas = listOf(
        Persona("Carla", R.drawable.imagen_carla),
        Persona("Juan", R.drawable.image_juan),
        Persona("Paco", R.drawable.image_paco)
    )
    LazyVerticalGrid(columns = GridCells.Fixed(3), contentPadding = PaddingValues(8.dp)) {
        items(personas) { persona ->
            PersonaCard(name = persona.name, image = persona.image)
        }
    }
}

@Composable
fun PersonaCard(name: String, image: Int) {
    Column(
        modifier = Modifier.padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = image),
            contentDescription = "Profile User Image",
            modifier = Modifier.size(100.dp)
        )
        Text(text = name)
    }
}