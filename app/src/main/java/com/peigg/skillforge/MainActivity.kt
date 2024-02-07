/*
    * Main Activity
 */
package com.peigg.skillforge

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

import androidx.compose.material3.*

import androidx.navigation.compose.rememberNavController

import com.peigg.skillforge.navigation.AppNavigation
import com.peigg.skillforge.theme.SkillForgeTheme
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

                ) { paddingValues ->
                    AppNavigation(navController, paddingValues)
                }
            }
        }
    }
}




