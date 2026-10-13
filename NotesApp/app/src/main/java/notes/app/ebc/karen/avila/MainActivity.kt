

package notes.app.ebc.karen.avila

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete

import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

import notes.app.ebc.karen.avila.ui.theme.NotesAppTheme


// ==========================================
// ACTIVIDAD PRINCIPAL
// ==========================================

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            NotesAppTheme {
                TareasRoute()
            }
        }
    }
}


// ==========================================
// PANTALLA PRINCIPAL DE TAREAS
// ==========================================

@Composable
fun TareaScreen(
    uiState: TareasUiState,
    onTextoTareaNuevaChange: (String) -> Unit,
    onAgregarTarea: () -> Unit,
    onCambiarEstado: (Int) -> Unit,
    onEliminar: (Int) -> Unit,
    onCambiarFiltro: (FiltroTarea) -> Unit
) {

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),

            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            // TITULO DE LA PANTALLA

            Text(
                text = "Mis Tareas",
                style = MaterialTheme.typography.headlineSmall
            )

            Spacer(
                modifier = Modifier.height(12.dp)
            )


            // CAMPO PARA ESCRIBIR NUEVA TAREA

            OutlinedTextField(
                value = uiState.textoNuevaTarea,

                onValueChange = onTextoTareaNuevaChange,

                label = {
                    Text("Nueva Tarea")
                },

                modifier = Modifier.fillMaxWidth(),

                singleLine = true
            )


            // BOTON AGREGAR TAREA

            Button(
                onClick = onAgregarTarea,
                modifier = Modifier.fillMaxWidth()
            ) {

                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Agregar tarea"
                )

                Spacer(
                    modifier = Modifier.width(8.dp)
                )

                Text(
                    text = "Agregar"
                )
            }


            Spacer(
                modifier = Modifier.height(8.dp)
            )


            // ==================================
            // FILTROS DE TAREAS
            // ==================================

            Row(
                modifier = Modifier.fillMaxWidth(),

                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                // FILTRO TODAS

                FilterChip(
                    selected = uiState.filtro == FiltroTarea.TODAS,

                    onClick = {
                        onCambiarFiltro(FiltroTarea.TODAS)
                    },

                    label = {
                        Text(text = "Todas")
                    }
                )


                // FILTRO PENDIENTES

                FilterChip(
                    selected = uiState.filtro == FiltroTarea.PENDIENTES,

                    onClick = {
                        onCambiarFiltro(FiltroTarea.PENDIENTES)
                    },

                    label = {
                        Text(text = "Pendientes")
                    }
                )


                // FILTRO COMPLETADAS

                FilterChip(
                    selected = uiState.filtro == FiltroTarea.COMPLETADAS,

                    onClick = {
                        onCambiarFiltro(FiltroTarea.COMPLETADAS)
                    },

                    label = {
                        Text(text = "Completadas")
                    }
                )
            }


            // ==================================
            // LISTA DE TAREAS
            // ==================================

            LazyColumn(
                modifier = Modifier.fillMaxWidth(),

                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {

                items(
                    items = uiState.tareasFiltradas,
                    key = { tarea -> tarea.id }
                ) { tarea ->

                    TareaItem(
                        tarea = tarea,

                        onCambioEstado = {
                            onCambiarEstado(tarea.id)
                        },

                        onEliminar = {
                            onEliminar(tarea.id)
                        }
                    )
                }
            }
        }
    }
}


// ==========================================
// COMPONENTE INDIVIDUAL DE TAREA
// ==========================================

@Composable
fun TareaItem(
    tarea: Tarea,
    onCambioEstado: () -> Unit,
    onEliminar: () -> Unit
) {

    Row(
        modifier = Modifier.fillMaxWidth(),

        verticalAlignment = Alignment.CenterVertically
    ) {

        // CHECKBOX PARA COMPLETAR TAREA

        Checkbox(
            checked = tarea.completada,

            onCheckedChange = {
                onCambioEstado()
            }
        )


        // TEXTO DE LA TAREA
        // SE TACHA SI ESTA COMPLETADA

        Text(
            text = tarea.nombre,

            modifier = Modifier.weight(1f),

            style = MaterialTheme.typography.bodyMedium,

            textDecoration = if (tarea.completada) {
                TextDecoration.LineThrough
            } else {
                TextDecoration.None
            }
        )


        // BOTON ELIMINAR TAREA

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


// ==========================================
// VISTA PREVIA DE PANTALLA
// ==========================================

@Preview(showBackground = true)
@Composable
fun TareaScreenPreview() {

    NotesAppTheme {

        TareaScreen(
            uiState = TareasUiState(
                tareas = listOf(

                    Tarea(
                        id = 1,
                        nombre = "Alimentar al Maxi",
                        completada = true
                    ),

                    Tarea(
                        id = 2,
                        nombre = "Hacer la tarea de Android",
                        completada = false
                    ),

                    Tarea(
                        id = 3,
                        nombre = "Estudiar Kotlin",
                        completada = true
                    )
                )
            ),

            onTextoTareaNuevaChange = {},
            onAgregarTarea = {},
            onCambiarEstado = {},
            onEliminar = {},
            onCambiarFiltro = {}
        )
    }
}


// ==========================================
// VISTA PREVIA DE UNA TAREA
// ==========================================

@Preview(showBackground = true)
@Composable
fun TareaItemPreview() {

    NotesAppTheme {

        TareaItem(
            tarea = Tarea(
                id = 1,
                nombre = "Alimentar al Maxi",
                completada = true
            ),

            onCambioEstado = {},
            onEliminar = {}
        )
    }
}
