
package notes.app.ebc.karen.muffinshop.model

enum class Sabor(
    val precio: Int
) {
    VAINILLA(precio = 30),
    CHOCOLATE(precio = 35),
    ARANDANOS(precio = 40)
}

enum class Relleno(
    val nombre: String,
    val extra: Int
) {
    SIN_RELLENO(nombre = "Sin relleno", extra = 0),
    CHOCOLATE(nombre = "Crema de chocolate", extra = 10),
    VAINILLA(nombre = "Crema de vainilla", extra = 8),
    FRUTOS_ROJOS(nombre = "Mermelada de frutos rojos", extra = 9)
}

enum class Topping(
    val nombre: String,
    val extra: Int
) {
    SIN_TOPPINGS(nombre = "Sin toppings", extra = 0),
    CHISPAS(nombre = "Chispas de chocolate", extra = 6),
    CONFETI(nombre = "Chispas de colores", extra = 5),
    CREMA(nombre = "Crema batida", extra = 7),
    FRUTA(nombre = "Fruta", extra = 8)
}

enum class Paso(
    val ruta: String,
    val titulo: String
) {
    SABOR(ruta = "sabor", titulo = "Elige el sabor"),
    RELLENO(ruta = "relleno", titulo = "Elige el relleno"),
    TOPPINGS(ruta = "toppings", titulo = "Elige los toppings"),
    CANTIDAD(ruta = "cantidad", titulo = "Indica la cantidad"),
    CLIENTE(ruta = "cliente", titulo = "Tus datos"),
    RESUMEN(ruta = "resumen", titulo = "Resumen de pedido"),
    EXITO(ruta = "exito", titulo = "¡Orden enviada!")
}
