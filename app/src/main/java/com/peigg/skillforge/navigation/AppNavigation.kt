// AppNavigation.kt
/*Este archivo contiene la lógica de navegación de la aplicación. Para eso usaremos un
  NavHostController y el composable AppNavigation que contendrá las rutas
  de la aplicación.
 */
package com.peigg.skillforge.navigation


import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.peigg.skillforge.features.ui.startscreen.StartScreen
import androidx.compose.ui.Modifier
import com.peigg.skillforge.features.ui.skillForgeScreen.navigateToSkillForgeScreen
import com.peigg.skillforge.features.ui.skillForgeScreen.skillForgeScreen



@Composable
fun AppNavigation(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "start_screen") {
        composable("start_screen") {
            StartScreen(navigateToSkillForgeScreen = { navController.navigateToSkillForgeScreen() })
        }
        skillForgeScreen(navController) // Llamada a la función definida en SkillForgeScreenNavigation.kt
    }
}