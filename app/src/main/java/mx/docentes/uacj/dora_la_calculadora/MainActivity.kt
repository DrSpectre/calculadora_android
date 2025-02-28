package mx.docentes.uacj.dora_la_calculadora

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import mx.docentes.uacj.dora_la_calculadora.ui.theme.Dora_la_calculadoraTheme

data class BotonModelo(val id: String, var numero: String) {}

var hileras_de_botones_a_dibujar = arrayOf(
    arrayOf(
        BotonModelo("boton_9", "9"),
        BotonModelo("boton_8", "8"),
        BotonModelo("boton_7", "7"),
    ),
    arrayOf(
        BotonModelo("boton_6", "6"),
        BotonModelo("boton_5", "5"),
        BotonModelo("boton_4", "4"),
    ),
    arrayOf(
        BotonModelo("boton_3", "3"),
        BotonModelo("boton_2", "2"),
        BotonModelo("boton_1", "1"),
    ),
    arrayOf(
        BotonModelo("boton_punto", "."),
        BotonModelo("boton_0", "0"),
        BotonModelo("boton_operacion", "OP"),
    )

)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Dora_la_calculadoraTheme {
                Calculadora()
            }
        }
    }
}

@Composable
fun Calculadora() {
    var pantalla_calculadora = remember { mutableStateOf("0") }
    var estado_de_la_calculadora = remember { mutableStateOf("mostrar_numeros") }

    fun pulsar_boton(boton: BotonModelo){
        Log.v("BOTONES_INTERFAZ", "Se ha pulsado el boton ${boton.id} de la interfaz")
        if(boton.id == "boton_operacion" ){
            estado_de_la_calculadora.value = "mostrar_operaciones"
        }

        if(estado_de_la_calculadora.value == "mostrar_numeros"){
            pantalla_calculadora.value = boton.numero
        }
        else{
            pantalla_calculadora.value = boton.id
        }

    }

    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center) {
        Text(text = "${pantalla_calculadora.value}", modifier = Modifier
                .padding(10.dp)
                    .fillMaxWidth()
                    .background(Color.Blue)
                    .height(50.dp),
            textAlign = TextAlign.Right,
            color = Color.White
        )

        // Deberia jugar mas con el estilo de aqui
        Column(modifier = Modifier.fillMaxSize().background(Color.DarkGray)) {
            for(fila_de_botones in hileras_de_botones_a_dibujar){
                Row(horizontalArrangement = Arrangement.SpaceAround,
                    modifier = Modifier.fillMaxWidth()) {
                    for(boton_a_dibujar in fila_de_botones){
                        Boton(boton_a_dibujar.numero, alPulsar = {
                            pulsar_boton(boton_a_dibujar)
                        })
                    }
                }
            }
        }
    }


}

@Composable
fun Boton(etiqueta: String, alPulsar: () -> Unit = {}){
    Button(onClick = alPulsar) {
        Image(
            painter = painterResource(R.drawable.conde_1),
            contentDescription = "Una foto de perfil del conde de contar",
            modifier = Modifier.size(25.dp)
        )

        Text(
            etiqueta, modifier = Modifier
                .background(Color.Green),
            textAlign = TextAlign.Center,
            color = Color.Red
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Dora_la_calculadoraTheme {
        Calculadora()
    }
}

@Preview(showBackground = true)
@Composable
fun mostrar_boton(){
    Dora_la_calculadoraTheme {
        Boton("4")
    }
}
