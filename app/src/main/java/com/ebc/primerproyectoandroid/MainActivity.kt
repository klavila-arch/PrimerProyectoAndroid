package com.ebc.primerproyectoandroid

import android.media.Ringtone
import android.media.RingtoneManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
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


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MeGustaApp()
        }
    }
}


@Preview(showBackground = true)
@Composable
fun MeGustaApp() {

    // Estados de nuestra aplicación
    var modoOscuro by remember { mutableStateOf(true) }
    var mostrarSaludos by remember { mutableStateOf(true) }
    var meGusta by remember { mutableStateOf(false) }

    // Contexto para poder reproducir la alarma
    val context = LocalContext.current

    // Guardamos el sonido para poder detenerlo
    var ringtone by remember {
        mutableStateOf<Ringtone?>(null)
    }

    // Colores dependiendo del modo
    val fondo = if (modoOscuro) {
        Color.Black
    } else {
        Color.White
    }

    val colorTexto = if (modoOscuro) {
        Color.Magenta
    } else {
        Color(0xFF7B1FA2)
    }


    // MATERIAL THEME
    MaterialTheme {

        // SURFACE
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = fondo
        ) {

            // COLUMN
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(32.dp),

                verticalArrangement = Arrangement.Center,

                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                // TEXTO PRINCIPAL
                Text(
                    text = "¡Bienvenida a mi app!",
                    color = colorTexto,
                    style = MaterialTheme.typography.headlineLarge
                )


                // SALUDOS
                if (mostrarSaludos) {

                    Text(
                        text = "¡Hola Maxi!",
                        color = colorTexto,
                        style = MaterialTheme.typography.headlineLarge,
                        modifier = Modifier.padding(top = 20.dp)
                    )

                    Text(
                        text = "¡Hola Sophia!",
                        color = colorTexto,
                        style = MaterialTheme.typography.headlineLarge,
                        modifier = Modifier.padding(top = 10.dp)
                    )
                }


                // BOTÓN SALUDAR
                Button(
                    onClick = {
                        mostrarSaludos = !mostrarSaludos
                    },
                    modifier = Modifier.padding(top = 20.dp)
                ) {
                    Text("Saludar")
                }


                // BOTÓN CAMBIAR MODO
                Button(
                    onClick = {
                        modoOscuro = !modoOscuro
                    },
                    modifier = Modifier.padding(top = 10.dp)
                ) {

                    Text(
                        if (modoOscuro) {
                            "Cambiar a modo claro"
                        } else {
                            "Cambiar a modo oscuro"
                        }
                    )
                }


                // BOTÓN ME GUSTA
                Button(
                    onClick = {
                        meGusta = !meGusta
                    },
                    modifier = Modifier.padding(top = 10.dp)
                ) {

                    Text(
                        if (meGusta) {
                            "💜 Me gusta"
                        } else {
                            "Me gusta"
                        }
                    )
                }


                // BOTÓN PARA ENCENDER LA ALARMA
                Button(
                    onClick = {

                        val alarma = RingtoneManager.getDefaultUri(
                            RingtoneManager.TYPE_ALARM
                        )

                        ringtone = RingtoneManager.getRingtone(
                            context,
                            alarma
                        )

                        ringtone?.play()
                    },
                    modifier = Modifier.padding(top = 10.dp)
                ) {
                    Text("Probar alarma")
                }


                // BOTÓN PARA APAGAR LA ALARMA
                Button(
                    onClick = {
                        ringtone?.stop()
                    },
                    modifier = Modifier.padding(top = 10.dp)
                ) {
                    Text("Apagar alarma")
                }
            }
        }
    }
}
