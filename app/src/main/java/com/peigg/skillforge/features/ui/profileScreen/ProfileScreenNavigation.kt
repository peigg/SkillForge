package com.peigg.skillforge.features.ui.profileScreen

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

val ROUTE = "profile_screen"

fun NavController.navigateToProfileScreen() {
    navigate(ROUTE)
}

fun NavGraphBuilder.profileScreen(navController: NavController) {
    composable(ROUTE) {
        ProfileScreen(navController = navController)
    }
}
