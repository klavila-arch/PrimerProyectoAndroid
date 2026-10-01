package com.ebc.primerproyectoandroid

import android.media.Ringtone
import android.media.RingtoneManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


// --------------------------------------------------
// MÁQUINA DE ESTADOS
// --------------------------------------------------

enum class EstadoApp {
    INICIO,
    SALUDANDO,
    ALARMA_ACTIVA,
    ALARMA_APAGADA
}


// --------------------------------------------------
// MAIN ACTIVITY
// --------------------------------------------------

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MeGustaApp()
        }
    }
}


// --------------------------------------------------
// APLICACIÓN PRINCIPAL
// --------------------------------------------------

@Preview(showBackground = true)
@Composable
fun MeGustaApp() {

    // Estado para modo claro / oscuro
    var modoOscuro by remember {
        mutableStateOf(false)
    }

    // Estado para mostrar los saludos
    var mostrarSaludos by remember {
        mutableStateOf(false)
    }

    // Contador de likes
    var contadorLikes by remember {
        mutableStateOf(0)
    }

    // Máquina de estados
    var estadoApp by remember {
        mutableStateOf(EstadoApp.INICIO)
    }

    // Contexto de Android
    val context = LocalContext.current

    // Sonido de alarma
    val alarmaUri = RingtoneManager.getDefaultUri(
        RingtoneManager.TYPE_ALARM
    )

    val ringtone: Ringtone? = remember {
        RingtoneManager.getRingtone(
            context,
            alarmaUri
        )
    }


    // --------------------------------------------------
    // MATERIAL THEME
    // --------------------------------------------------

    MaterialTheme(
        colorScheme = if (modoOscuro) {
            darkColorScheme()
        } else {
            lightColorScheme()
        }
    ) {

        // --------------------------------------------------
        // SURFACE
        // --------------------------------------------------

        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {

            // --------------------------------------------------
            // COLUMN
            // --------------------------------------------------

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(all = 32.dp),

                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                // --------------------------------------------------
                // HOLA ANDROID
                // --------------------------------------------------

                Text(
                    text = "¡Hola Android!",
                    style = MaterialTheme.typography.headlineLarge,
                    color = Color.Magenta
                )


                Spacer(
                    modifier = Modifier.height(12.dp)
                )


                // --------------------------------------------------
                // ESTADO ACTUAL
                // --------------------------------------------------

                Text(
                    text = "Estado: $estadoApp",
                    style = MaterialTheme.typography.bodyLarge
                )


                Spacer(
                    modifier = Modifier.height(12.dp)
                )


                // --------------------------------------------------
                // BOTÓN SALUDAR
                // --------------------------------------------------

                Button(
                    onClick = {

                        mostrarSaludos = !mostrarSaludos

                        estadoApp = if (mostrarSaludos) {
                            EstadoApp.SALUDANDO
                        } else {
                            EstadoApp.INICIO
                        }
                    }
                ) {

                    Text("Saludar")
                }


                // --------------------------------------------------
                // SALUDOS MAXI Y SOPHIA
                // --------------------------------------------------

                if (mostrarSaludos) {

                    Spacer(
                        modifier = Modifier.height(10.dp)
                    )

                    Greeting(
                        name = "Maxi",
                        modifier = Modifier.background(
                            Color.Magenta
                        )
                    )


                    Spacer(
                        modifier = Modifier.height(10.dp)
                    )


                    Greeting(
                        name = "Sophia",
                        modifier = Modifier.background(
                            Color.Green
                        )
                    )
                }


                Spacer(
                    modifier = Modifier.height(10.dp)
                )


                // --------------------------------------------------
                // CAMBIAR MODO CLARO / OSCURO
                // --------------------------------------------------

                Button(
                    onClick = {
                        modoOscuro = !modoOscuro
                    }
                ) {

                    Text(
                        if (modoOscuro) {
                            "Cambiar a modo claro"
                        } else {
                            "Cambiar a modo oscuro"
                        }
                    )
                }


                Spacer(
                    modifier = Modifier.height(10.dp)
                )


                // --------------------------------------------------
                // CONTADOR DE LIKES
                // --------------------------------------------------

                Text(
                    text = "Likes: $contadorLikes",
                    style = MaterialTheme.typography.titleLarge
                )


                Spacer(
                    modifier = Modifier.height(6.dp)
                )


                Button(
                    onClick = {
                        contadorLikes++
                    }
                ) {

                    Text("Me gusta")
                }


                Spacer(
                    modifier = Modifier.height(10.dp)
                )


                // --------------------------------------------------
                // PROBAR ALARMA
                // --------------------------------------------------

                Button(
                    onClick = {

                        ringtone?.play()

                        estadoApp = EstadoApp.ALARMA_ACTIVA
                    }
                ) {

                    Text("Probar alarma")
                }


                Spacer(
                    modifier = Modifier.height(10.dp)
                )


                // --------------------------------------------------
                // APAGAR ALARMA
                // --------------------------------------------------

                Button(
                    onClick = {

                        ringtone?.stop()

                        estadoApp = EstadoApp.ALARMA_APAGADA
                    }
                ) {

                    Text("Apagar alarma")
                }
            }
        }
    }
}


// --------------------------------------------------
// FUNCIÓN GREETING
// --------------------------------------------------

@Composable
fun Greeting(
    name: String,
    modifier: Modifier = Modifier
) {

    Text(
        text = "¡Hola $name!",
        modifier = modifier.padding(all = 8.dp),
        style = MaterialTheme.typography.headlineLarge
    )
}


// --------------------------------------------------
// PREVIEW
// --------------------------------------------------

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {

    MaterialTheme {

        Surface {

            Column(
                modifier = Modifier.padding(all = 32.dp)
            ) {

                Greeting(
                    name = "Maxi",
                    modifier = Modifier.background(
                        Color.Magenta
                    )
                )


                Spacer(
                    modifier = Modifier.height(10.dp)
                )


                Greeting(
                    name = "Sophia",
                    modifier = Modifier.background(
                        Color.Green
                    )
                )
            }
        }
    }
}