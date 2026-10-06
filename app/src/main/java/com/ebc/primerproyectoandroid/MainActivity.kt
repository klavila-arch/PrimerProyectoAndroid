package com.ebc.primerproyectoandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

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

    // Estado para controlar el contador de "Me gusta"
    var contador by remember {
        mutableIntStateOf(0)
    }

    // Estado para controlar el modo claro y oscuro
    var modoOscuro by remember {
        mutableStateOf(false)
    }

    // Selecciona los colores dependiendo del modo
    val colores = if (modoOscuro) {
        darkColorScheme()
    } else {
        lightColorScheme()
    }

    MaterialTheme(
        colorScheme = colores
    ) {

        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {

            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                // Texto principal
                Text(
                    text = "¡Hola Android!",
                    style = MaterialTheme.typography.headlineLarge
                )

                // Contador
                Text(
                    text = "$contador Me gusta"
                )

                // Botón Me gusta
                Button(
                    onClick = {
                        contador++
                    }
                ) {

                    Icon(
                        imageVector = Icons.Default.ThumbUp,
                        contentDescription = "Me gusta"
                    )

                    Text(
                        text = " Me gusta"
                    )
                }

                // Switch para cambiar entre modo claro y oscuro
                Switch(
                    checked = modoOscuro,

                    onCheckedChange = { valorSwitch ->
                        modoOscuro = valorSwitch
                    },

                    thumbContent = {
                        Icon(
                            imageVector = if (modoOscuro) {
                                Icons.Default.DarkMode
                            } else {
                                Icons.Default.LightMode
                            },

                            contentDescription = "Cambiar modo de color",

                            modifier = Modifier.size(
                                SwitchDefaults.IconSize
                            )
                        )
                    }
                )
            }
        }
    }
}