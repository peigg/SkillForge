package com.peigg.skillforge.features.ui.startscreen

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.peigg.skillforge.features.ui.skillForgeScreen.navigateToSkillForgeScreen


fun NavGraphBuilder.startScreen(navController: NavController) {
    composable("start_screen") { // Asegúrate de que la ruta sea la misma que en AppNavigation
        StartScreen(navigateToSkillForgeScreen = { navController.navigateToSkillForgeScreen() })
    }
}

fun NavController.navigateToSkillForgeScreen() {
    navigate("skillforge_screen")
}