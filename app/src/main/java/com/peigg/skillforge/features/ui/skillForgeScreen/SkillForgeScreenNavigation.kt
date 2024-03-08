package com.peigg.skillforge.features.ui.skillForgeScreen


import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable


val ROUTE = "skillforge_screen"

fun NavController.navigateToSkillForgeScreen() {
    navigate(ROUTE)
}
fun NavGraphBuilder.skillForgeScreen(navController: NavController) {
    composable("skillforge_screen") {
        SkillForgeScreen(navController = navController)
    }
}