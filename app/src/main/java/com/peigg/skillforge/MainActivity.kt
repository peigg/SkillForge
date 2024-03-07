/*
    * Main Activity
 */
package com.peigg.skillforge

import android.content.ContentValues
import android.content.Context
import android.os.Build.VERSION_CODES.S
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding

import androidx.compose.material3.*
import androidx.compose.ui.Modifier

import androidx.navigation.compose.rememberNavController
import com.peigg.skillforge.data.bd.DbHelper
import com.peigg.skillforge.data.bd.SQL_CREATE_ENTRIES
import com.peigg.skillforge.data.bd.SkillForgeDatabase

import com.peigg.skillforge.navigation.AppNavigation
import com.peigg.skillforge.theme.SkillForgeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
   /*function that is called when the activity is created.Uses the setContent() function to set the content
    of the activity to the composable function that is passed to it.
    */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

/*
       val dbHelper = DbHelper(this)
       val db = dbHelper.readableDatabase


       val values = ContentValues().apply {
           put(SkillForgeDatabase.COLUMN_NAME, "Juan Pérez")
           put(SkillForgeDatabase.COLUMN_IMAGE, R.drawable.image_juan)
           put(SkillForgeDatabase.COLUMN_SPEC, "Desarrollo de aplicaciones móviles")
           put(SkillForgeDatabase.COLUMN_DESCRIPTION, "Soy un desarrollador de aplicaciones móviles con 5 años de experiencia. He trabajado en proyectos de gran envergadura y me encantaría ayudarte a mejorar tus habilidades.")
           put(SkillForgeDatabase.COLUMN_PRICE, "S/ 100")
       }

 db.insert(SkillForgeDatabase.TABLE_NAME, null, values)

       db.execSQL(
           """
              INSERT INTO ${SkillForgeDatabase.TABLE_NAME} (${SkillForgeDatabase.COLUMN_NAME}, ${SkillForgeDatabase.COLUMN_IMAGE}, ${SkillForgeDatabase.COLUMN_SPEC}, ${SkillForgeDatabase.COLUMN_DESCRIPTION}, ${SkillForgeDatabase.COLUMN_PRICE})
                VALUES ('Juan Sanchez', ${R.drawable.image_paco}, 'Desarrollo de aplicaciones móviles', 'Soy un desarrollador de aplicaciones móviles con 5 años de experiencia. He trabajado en proyectos de gran envergadura y me encantaría ayudarte a mejorar tus habilidades.', 'S/ 100')
           """.trimIndent()
       )*/
        setContent {
            SkillForgeTheme {

                // Scaffold is a layout composable that provides a material design scaffold for the app.
                Scaffold(

                ) {
                    //padding is a parameter that represents the padding values of the scaffold.
                    padding ->
                    // AppNavigation is a composable function that is used to navigate between different screens.
                    AppNavigation(modifier = Modifier.padding(padding))
                }
            }
        }
    }


}




