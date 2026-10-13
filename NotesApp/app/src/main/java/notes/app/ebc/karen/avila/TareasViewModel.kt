
package notes.app.ebc.karen.avila

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class TareasViewModel : ViewModel() {

    // ESTADO PRIVADO
    private val _uiState = MutableStateFlow(TareasUiState())

    // ESTADO PUBLICO
    val uiState: StateFlow<TareasUiState> =
        _uiState.asStateFlow()

    // IDENTIFICADOR DE TAREAS
    private var siguienteId = 1

    // ACTUALIZAR TEXTO
    fun actualizarTextoCajaNuevaTarea(texto: String) {
        _uiState.update { estadoActual ->
            estadoActual.copy(
                textoNuevaTarea = texto
            )
        }
    }

    // AGREGAR TAREA
    fun agregarTarea() {

        val textoCaja = _uiState.value.textoNuevaTarea.trim()

        if (textoCaja.isEmpty()) {
            return
        }

        val nuevaTarea = Tarea(
            id = siguienteId++,
            nombre = textoCaja,
            completada = false
        )

        _uiState.update { estadoActual ->
            estadoActual.copy(
                tareas = estadoActual.tareas + nuevaTarea,
                textoNuevaTarea = ""
            )
        }
    }

    // CAMBIAR ESTADO

    fun cambioEstadoTarea(id: Int) {
        // Cambiar el estado de una tarea

        _uiState.update {
            it.copy(
                tareas = it.tareas.map { aux ->
                    if (aux.id == id) {
                        aux.copy(
                            completada = !aux.completada
                        )
                    } else {
                        aux
                    }
                }
            )
        }
    }

    // ELIMINAR TAREA
    fun eliminarTarea(id: Int) {
        _uiState.update { estadoActual ->
            estadoActual.copy(
                tareas = estadoActual.tareas.filter {
                    it.id != id
                }
            )
        }
    }

    // CAMBIAR FILTRO
    fun cambiarFiltro(nuevoFiltro: FiltroTarea) {
        _uiState.update { estadoActual ->
            estadoActual.copy(
                filtro = nuevoFiltro
            )
        }
    }
}

