
package notes.app.ebc.karen.muffinshop.viewmodel

import androidx.lifecycle.ViewModel
import notes.app.ebc.karen.muffinshop.model.PedidoUiState
import notes.app.ebc.karen.muffinshop.model.Paso
import notes.app.ebc.karen.muffinshop.model.Relleno
import notes.app.ebc.karen.muffinshop.model.Sabor
import notes.app.ebc.karen.muffinshop.model.Topping
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class PedidoViewModel : ViewModel() {

    // 1. Estado del pedido
    private val _uiState = MutableStateFlow(
        PedidoUiState()
    )

    val uiState: StateFlow<PedidoUiState> =
        _uiState.asStateFlow()

    // 2. Seleccionar sabor
    fun seleccionarSabor(sabor: Sabor) {
        _uiState.update {
            it.copy(
                sabor = sabor
            )
        }
    }

    // 3. Seleccionar relleno
    fun seleccionarRelleno(relleno: Relleno) {
        _uiState.update {
            it.copy(
                relleno = relleno
            )
        }
    }

    // 4. Alternar toppings
    fun alternarTopping(topping: Topping) {
        _uiState.update { estado ->

            val actuales = estado.toppings

            val nuevos = when {
                topping == Topping.SIN_TOPPINGS -> {
                    setOf(Topping.SIN_TOPPINGS)
                }

                topping in actuales -> {
                    actuales - topping
                }

                else -> {
                    (actuales - Topping.SIN_TOPPINGS) + topping
                }
            }

            estado.copy(
                toppings = nuevos
            )
        }
    }

    // 5. Cambiar cantidad
    fun cambiarCantidad(nuevaCantidad: Int) {
        _uiState.update {
            it.copy(
                cantidad = nuevaCantidad.coerceIn(
                    1,
                    12
                )
            )
        }
    }

    // 6. Cambiar nombre del cliente
    fun cambiarNombre(nombre: String) {
        _uiState.update {
            it.copy(
                nombre = nombre
            )
        }
    }

    // 7. Cambiar teléfono del cliente
    fun cambiarTelefono(telefono: String) {
        _uiState.update {
            it.copy(
                telefono = telefono
                    .filter(Char::isDigit)
                    .take(10)
            )
        }
    }

    // 8. Cambiar correo del cliente
    fun cambiarCorreo(correo: String) {
        _uiState.update {
            it.copy(
                correo = correo
            )
        }
    }

    // 9. Confirmar el pedido
    fun confirmarPedido() {
        _uiState.update { estado ->
            if (
                estado.esValido(
                    paso = Paso.RESUMEN
                )
            ) {
                estado.copy(
                    enviada = true
                )
            } else {
                estado
            }
        }
    }

    // 10. Reiniciar el pedido
    fun reiniciar() {
        _uiState.value =
            PedidoUiState()
    }
}
