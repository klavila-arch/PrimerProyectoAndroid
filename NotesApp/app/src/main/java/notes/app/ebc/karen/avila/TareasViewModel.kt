
package notes.app.ebc.karen.avila

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class TareasViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(TareasUiState())

    val uiState: StateFlow<TareasUiState> = _uiState.asStateFlow()

    private var siguienteId = 1

    fun actualizarTextoCajaNuevaTarea(texto: String) {
        // TODO: Actualizar texto que viene de la caja en vista
    }

    fun agregarTarea() {
        // TODO: Tomo el texto de la vista y formo una nueva tarea
    }

    fun cambioEstadoTarea(id: Int) {
        // TODO: Cambiar el estado de una Tarea
    }

    fun eliminarTarea(id: Int) {
        // TODO: Eliminar tarea
    }

    fun cambiarFiltro(filtro: FiltroTarea) {
        // TODO: Cambiar el listado en vista a tareas correspondientes
    }
}



