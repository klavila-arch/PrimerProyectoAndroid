
package notes.app.ebc.karen.avila

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import notes.app.ebc.karen.avila.ui.theme.NotesAppTheme

// ACTIVIDAD PRINCIPAL

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            NotesAppTheme {
                TareaScreen()
            }
        }
    }
}

// PANTALLA PRINCIPAL DE TAREAS

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TareaScreen(
    modifier: Modifier = Modifier,
    uiState: TareasUiState = TareasUiState(),
    onTextoTareaNuevaChange: (String) -> Unit = {},
    onAgregarTarea: () -> Unit = {}
) {

    var textoLocal by remember {
        mutableStateOf(uiState.textoNuevaTarea)
    }

    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Mis Tareas")
                }
            )
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
        ) {

            // CAMPO PARA ESCRIBIR NUEVA TAREA

            OutlinedTextField(
                value = textoLocal,
                onValueChange = {
                    textoLocal = it
                    onTextoTareaNuevaChange(it)
                },
                label = {
                    Text(text = "Nueva Tarea")
                },
                modifier = Modifier.fillMaxWidth()
            )

            // BOTÓN PARA AGREGAR TAREA

            Button(
                onClick = onAgregarTarea,
                modifier = Modifier.fillMaxWidth()
            ) {

                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Agregar nueva tarea"
                )

                Text(text = " Agregar")
            }

            // FILTROS DE TAREAS

            Row {
                // TODO: Implementar chips de filtro
            }

            // LISTADO DE TAREAS

            LazyColumn {
                // TODO: Implementar listado de tareas
            }
        }
    }
}

// COMPONENTE INDIVIDUAL DE TAREA

@Composable
fun TareaItem(
    texto: String = "Darle de cenar al maxi",
    completada: Boolean = false,
    onCompletadaChange: (Boolean) -> Unit = {},
    onEliminar: () -> Unit = {}
) {

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {

        // CASILLA PARA COMPLETAR TAREA

        Checkbox(
            checked = completada,
            onCheckedChange = onCompletadaChange
        )

        // TEXTO DE LA TAREA

        Text(
            text = texto,
            modifier = Modifier.weight(1f)
        )

        // BOTÓN PARA ELIMINAR TAREA

        IconButton(
            onClick = onEliminar
        ) {

            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "Eliminar tarea"
            )
        }
    }
}

// VISTA PREVIA DE LA PANTALLA PRINCIPAL

@Preview(showBackground = true)
@Composable
fun TareaScreenPreview() {

    NotesAppTheme {
        TareaScreen()
    }
}

// VISTA PREVIA DE UNA TAREA INDIVIDUAL

@Preview(showBackground = true)
@Composable
fun TareaItemPreview() {

    NotesAppTheme {
        TareaItem()
    }
}


