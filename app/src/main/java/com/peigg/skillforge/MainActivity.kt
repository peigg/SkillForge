package com.peigg.skillforge

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import com.peigg.skillforge.ui.theme.SkillForgeTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SkillForgeTheme {
                Scaffold(
                    topBar = {
                        TopAppBar(title = { Text("SkillForge") },
                            navigationIcon = {
                                // El logo ahora aparece como un icono en la AppBar.
                                Image(
                                    painter = painterResource(id = R.drawable.skillforge_logo),
                                    contentDescription = "SkillForge Logo",
                                    modifier = Modifier
                                        .size(40.dp) // Ajusta el tamaño según sea necesario
                                        .padding(8.dp)
                                )
                            })
                    },
                    bottomBar = {
                        BottomAppBar { /* Add BottomBar content here if needed */ }
                    }
                ) { paddingValues ->
                    SkillForgeScreen(modifier = Modifier.padding(paddingValues))
                }
            }
        }
    }

    @Composable
    private fun SkillForgeScreen(modifier: Modifier) {
        Column(
            modifier = modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(20.dp))
            PersonaList()
        }
    }


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

    data class Persona(val name: String, val image: Int)

    @Composable
    fun PersonaCard(name: String, image: Int) {
        Column(
            modifier = Modifier.padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally // Alinea el texto debajo de la imagen centrado horizontalmente
        ) {
            Image(
                painter = painterResource(id = image),
                contentDescription = "Profile User Image",
                modifier = Modifier.size(100.dp) // Puedes ajustar el tamaño de la imagen aquí
            )
            Text(text = name)
        }
    }
}

