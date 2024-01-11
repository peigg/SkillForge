package com.peigg.skillforge

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.material3.icons.Icons
import androidx.compose.material3.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.peigg.skillforge.ui.theme.SkillForgeTheme

class MainActivity : ComponentActivity() {

    var presses by mutableStateOf(0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SkillForgeTheme {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = MaterialTheme.colorScheme.primaryContainer,
                                titleContentColor = MaterialTheme.colorScheme.primary,
                            ),
                            title = {
                                Text("Top app bar")
                            }
                        )
                    },
                    bottomBar = {
                        BottomAppBar(
                            containerColor = MaterialTheme.colorScheme.primaryContainer,
                            contentColor = MaterialTheme.colorScheme.primary,
                        ) {
                            Text(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                textAlign = TextAlign.Center,
                                text = "Bottom app bar",
                            )
                        }
                    },
                    floatingActionButton = {
                        FloatingActionButton(onClick = {
                            presses++
                        }) {
                            Icon(Icons.Default.Add, contentDescription = "Add")
                        }
                    }
                ) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .padding(innerPadding),
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                    ) {
                        Text(
                            modifier = Modifier.padding(8.dp),
                            text =
                            """
                                This is SkillForge, a platform that connects IT students with mentors.
                            """.trimIndent(),
                        )
                    }
                }
            }
        }
    }

    @Composable
    fun Scaffold(
        topBar: @Composable (innerPadding: PaddingValues) -> Unit,
        bottomBar: @Composable () -> Unit,
        floatingActionButton: @Composable () -> Unit,
        content: @Composable (innerPadding: PaddingValues) -> Unit
    ) {

    }

    @Composable
    fun TopAppBar(title: @Composable () -> Unit) {

    }

   /* @Composable
    fun MessageCard(name: String) {
        Text(text = "$name")
    }
    */

    @Composable
    fun BottomAppBar(
        containerColor: Color,
        contentColor: Color,
        content: @Composable () -> Unit
    ) {
        // Implementación de BottomAppBar
    }

    @Composable
    fun FloatingActionButton(
        onClick: () -> Unit,
        modifier: Modifier = Modifier,
        content: @Composable () -> Unit
    ) {
        // Implementación de FloatingActionButton
    }
}
