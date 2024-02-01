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
                        TopAppBar(title = { Text("SkillForge") })
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
            Text("Welcome to SkillForge", modifier = Modifier.padding(16.dp))
            Spacer(modifier = Modifier.height(10.dp))
            Image(
                painter = painterResource(id = R.drawable.skillforge_logo),
                contentDescription = "SkillForge Logo"
            )
            Spacer(modifier = Modifier.height(20.dp))
            PersonaList()
        }
    }

    @Composable
    fun PersonaList() {
        val personas = listOf(
            Persona("Pablo", "image_pablo"),
            Persona("Juan", "image_juan"),
            Persona("Paco", "image_paco")
        )
        LazyVerticalGrid(columns = GridCells.Fixed(3), contentPadding = PaddingValues(8.dp)) {
            items(personas) { persona ->
                PersonaCard(name = persona.name, image = persona.image)
            }
        }
    }

    data class Persona(val name: String, val image: String)

    @Composable
    fun PersonaCard(name: String, image: String) {
        Box(modifier = Modifier.padding(8.dp)) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_background), // Update this with actual image handling
                contentDescription = "Profile User Image"
            )
            Text(text = name, modifier = Modifier.align(Alignment.BottomCenter))
        }
    }
}
