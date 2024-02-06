package com.peigg.skillforge.screens.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn


import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.peigg.skillforge.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SkillForgeScreen(navController: NavController, paddingValues: PaddingValues) {
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        topBar = {
            AppTopBar(snackbarHostState)
        },
        bottomBar = {
            AppBottomBar()
        },
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(20.dp))
            PersonaList()
        }
    }
}


data class Persona(
    val name: String,
    val image: Int,
    val especializacion: String,
    val descripcion: String,
    val precio: String
)
@Composable
fun PersonaList() {
    val personas = listOf(
        // Añade tus objetos Persona aquí
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
            "Soy una desarrollador web con 3 años de experiencia. He trabajado en proyectos de gran envergadura y me encantaría ayudarte a mejorar tus habilidades.",
            "S/ 80"
        ),
        Persona(
            "Carla Sánchez",
            R.drawable.imagen_carla,
            "Desarrollo .NET",
            "Soy una desarrollador fullstack con 15 años de experiencia. He trabajado en proyectos de gran envergadura y me encantaría ayudarte a mejorar tus habilidades.",
            "S/ 120"
        ),
    )
    LazyColumn(contentPadding = PaddingValues(8.dp)) {
        items(personas) { persona ->
            PersonaCard(persona)
        }
    }
}



@Composable
fun PersonaCard(persona: Persona) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .border(4.dp, Color(0xFFA5ED0D), shape = MaterialTheme.shapes.medium),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF19BDFF))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Image(
                painter = painterResource(id = persona.image),
                contentDescription = "Profile User Image",
                modifier = Modifier
                    .size(100.dp)
                    .align(Alignment.CenterHorizontally)
            )
            Text(text = persona.name, color = Color.White, style = MaterialTheme.typography.titleMedium)
            Text(text = "Especialización: ${persona.especializacion}", color = Color.White, style = MaterialTheme.typography.bodyMedium)
            Text(text = "Descripción: ${persona.descripcion}", color = Color.White, style = MaterialTheme.typography.bodySmall)
            Text(text = "Precio/mes: ${persona.precio}", color = Color.White, style = MaterialTheme.typography.bodyLarge)
        }
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(snackbarHostState: SnackbarHostState) {
    var showMenu by remember { mutableStateOf(false) }

    TopAppBar(
        title = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(R.drawable.skillforge_logo),
                    contentDescription = "Logo de SkillForge",
                    modifier = Modifier.size(40.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text("SkillForge")
            }
        },
        actions = {
            IconButton(onClick = { showMenu = !showMenu }) {
                Icon(Icons.Filled.Menu, contentDescription = "Menú")
            }
            DropdownMenu(
                expanded = showMenu,
                onDismissRequest = { showMenu = false }
            ) {
                val menuOptions = listOf("Busca un mentor", "Especializaciones", "Login", "Sobre Nosotros", "Contacto")
                menuOptions.forEach { option ->
                    DropdownMenuItem(
                        text = { Text(option) },
                        onClick = {
                            showMenu = false
                            // Mostrar Snackbar "En construcción"
                            CoroutineScope(Dispatchers.Main).launch {
                                snackbarHostState.showSnackbar(
                                    message = "$option está en construcción",
                                    duration = SnackbarDuration.Short
                                )
                            }
                        }
                    )
                }
            }
        }
    )
}

@Composable
fun AppBottomBar() {
    BottomAppBar {
        // Aquí puedes añadir íconos o acciones para tu BottomAppBar
    }
}