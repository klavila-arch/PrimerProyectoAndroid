
package notes.app.ebc.karen.avila

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel


// ========================================
// CONEXION ENTRE PANTALLA Y VIEWMODEL
// ========================================

@Composable
fun TareasRoute(
    viewModel: TareasViewModel = viewModel()
) {

    // OBSERVAR EL ESTADO DE LAS TAREAS

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()


    // CONECTAR LA PANTALLA CON EL VIEWMODEL

    TareaScreen(
        uiState = uiState,

        onTextoTareaNuevaChange =
            viewModel::actualizarTextoCajaNuevaTarea,

        onAgregarTarea =
            viewModel::agregarTarea,

        onCambiarEstado =
            viewModel::cambioEstadoTarea,

        onEliminar =
            viewModel::eliminarTarea,

        onCambiarFiltro =
            viewModel::cambiarFiltro
    )
}
