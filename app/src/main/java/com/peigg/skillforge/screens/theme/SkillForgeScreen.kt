package com.peigg.skillforge.screens.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
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