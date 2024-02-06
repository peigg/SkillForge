package com.peigg.skillforge

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.peigg.skillforge.ui.AppNavigation
import com.peigg.skillforge.ui.SkillForgeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SkillForgeTheme {
                val navController = rememberNavController()
                Scaffold(
                    topBar = { AppTopBar() },
                    bottomBar = { AppBottomBar() },
                ) { paddingValues ->
                    AppNavigation(navController, paddingValues)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar() {
    TopAppBar(
        title = { Text("SkillForge") },
        navigationIcon = {
            Image(
                painter = painterResource(R.drawable.skillforge_logo),
                contentDescription = "SkillForge Logo",
                modifier = Modifier
                    .size(40.dp)
                    .padding(8.dp)
            )
        }
    )
}

@Composable
fun AppBottomBar() {
    BottomAppBar {
        // Aquí puedes añadir íconos o acciones para tu BottomAppBar
    }
}
