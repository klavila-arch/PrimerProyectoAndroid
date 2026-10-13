
package notes.app.ebc.karen.avila

data class TareasUiState(
    val textoNuevaTarea: String = "",
    val tareas: List<Tarea> = emptyList(),
    val filtro: FiltroTarea = FiltroTarea.TODAS
) {

    val tareasFiltradas: List<Tarea>
        get() {
            return when (filtro) {
                FiltroTarea.TODAS -> tareas

                FiltroTarea.PENDIENTES ->
                    tareas.filter { !it.completada }

                FiltroTarea.COMPLETADAS ->
                    tareas.filter { it.completada }
            }
        }
}

