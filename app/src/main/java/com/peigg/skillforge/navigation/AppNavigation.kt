// AppNavigation.kt
package com.peigg.skillforge.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.peigg.skillforge.screens.theme.SkillForgeScreen
import com.peigg.skillforge.screens.theme.StartScreen


@Composable
fun AppNavigation(navController: NavHostController, paddingValues: PaddingValues) {
    NavHost(navController = navController, startDestination = "splash") {
        composable("splash") { StartScreen(navController) }
        composable("home") { SkillForgeScreen(navController, paddingValues) }


    }
}
