// AppNavigation.kt
package com.peigg.skillforge.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.peigg.skillforge.screens.theme.SkillForgeScreen


@Composable
fun AppNavigation(navController: NavHostController, paddingValues: PaddingValues) {
    NavHost(navController = navController, startDestination = "home") {
        composable("home") { SkillForgeScreen(navController, paddingValues) }

        // Agrega más destinos según sea necesario
    }
}
