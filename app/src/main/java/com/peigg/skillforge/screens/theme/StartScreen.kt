/*
* Pantalla de inicio de la aplicación
 */
package com.peigg.skillforge.screens.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.peigg.skillforge.R

@Composable
fun StartScreen(navController: NavHostController) {
    val logo: Painter = painterResource(id = R.drawable.skillforge_logo)
    Box(
        modifier = Modifier
            .fillMaxSize()
            .clickable {
                navController.navigate("home") {
                    // Asegura que la splash screen no se pueda volver a visitar con el botón atrás
                    popUpTo("splash") { inclusive = true }
                    launchSingleTop = true
                }
            }, contentAlignment = Alignment.Center


    ) {
        Image(painter = logo, contentDescription = "App Logo",
              contentScale = androidx.compose.ui.layout.ContentScale.Crop,
              modifier = Modifier.fillMaxSize())

        Text(text ="Tap to continue",
            color = androidx.compose.ui.graphics.Color.White,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 100.dp))
    }
}
