/*
Este archivo contiene el código de la pantalla principal de la aplicación SkillForge.
 */

package com.peigg.skillforge.features.ui.skillForgeScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.peigg.skillforge.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SkillForgeScreen(navController: NavController, viewModel: SkillForgeScreenViewModel = hiltViewModel()) {
    val snackbarHostState = remember { SnackbarHostState() }
    val personas = viewModel.personas.value
    Scaffold(
        topBar = {
            AppTopBar(snackbarHostState)
        },
        bottomBar = {
            AppBottomBar(snackbarHostState, navController)
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
            PersonaList(personas = personas)
        }
    }
}
data class Persona(
    val name: String,
    val image: Int,
    val spec: String,
    val descripcion: String,
    val precio: String
)
@Composable
fun PersonaList(personas: List<Persona>) {
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
            Text(text = "Especialización: ${persona.spec}", color = Color.White, style = MaterialTheme.typography.bodyMedium)
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
fun AppBottomBar(snackbarHostState: SnackbarHostState,navController: NavController ) {
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.primaryContainer // Aquí cambias el color de fondo
    ) {
        var selectedItem by remember { mutableStateOf(0) }


        NavigationBarItem(
            icon = { Icon(Icons.Filled.Home, contentDescription = "Home") },
            label = { Text("Home") },
            selected = selectedItem == 0,
            onClick = {
                selectedItem = 0
              /*  navController.navigate("home") {
                    popUpTo = navController.graph.startDestinationId
                    launchSingleTop = true
                }*/
                CoroutineScope(Dispatchers.Main).launch {
                    snackbarHostState.showSnackbar("Home en construcción", duration = SnackbarDuration.Short)
                }
            }
        )

        NavigationBarItem(
            icon = { Icon(Icons.Filled.Search, contentDescription = "Buscar") },
            label = { Text("Buscar") },
            selected = selectedItem == 1,
            onClick = {
                selectedItem = 1
               /* navController.navigate("buscar") {
                    popUpTo = navController.graph.startDestinationId
                    launchSingleTop = true
                }*/
                CoroutineScope(Dispatchers.Main).launch {
                    snackbarHostState.showSnackbar("Buscar en construcción", duration = SnackbarDuration.Short)
                }
            }
        )

        NavigationBarItem(
            icon = { Icon(Icons.Filled.AccountCircle, contentDescription = "Perfil") },
            label = { Text("Perfil") },
            selected = selectedItem == 2,
            onClick = {
                selectedItem = 2
              /*  navController.navigate("perfil") {
                    popUpTo = navController.graph.startDestinationId
                    launchSingleTop = true
                } */
                CoroutineScope(Dispatchers.Main).launch {
                    snackbarHostState.showSnackbar("Perfil en construcción", duration = SnackbarDuration.Short)
                }
            }
        )
    }
}