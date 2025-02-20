package br.com.fiap.minhaidade

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
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.fiap.minhaidade.ui.theme.MinhaIdadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MinhaIdadeTheme {
                //estiliza outros compomente e é tipo um container
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
//                    Greeting("Lade")
                    MeuComponete()
                }
            }
        }
    }
}

@Composable
fun MeuComponete() {
    val corFiap = 0xFFAD1F4E

    //CRIANDO VARIAVAEL DE ESTADO
    var idade = remember { //opa, o valor da variavel mudou, e agora eu preciso me lembrar disso
        mutableStateOf(0)
    }

    var alertaTela = remember {
        mutableStateOf("Você é menor de idade")
    }

    fun verificaMaior(): Boolean {
        if (idade.value > 17) {
            alertaTela.value = "Você é maior de idade!"
            return true

        } else {
            alertaTela.value = "Você é menor de idade"
            return false
        }


    }

    @Composable
    fun retornaImagem() {
        Image(
            painter = painterResource(id = R.drawable.img),
            contentDescription = "Quer beber?",
            modifier = Modifier.size(200.dp),
            contentScale = ContentScale.Crop
        )
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = "Qual a sua idade?",
            fontSize = 24.sp,
            color = Color(0xFFAD1F4E),
            fontWeight = FontWeight.Bold,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        Text(
            text = "Escolha sua idade: ",
            fontSize = 12.sp,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(18.dp))
        Text(
            text = "${idade.value}",
            fontSize = 48.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(12.dp))
        Row() {
            Button(
                onClick = {

                    if (idade.value <= 0) {
                        alertaTela.value = "Idade inválida!"
                    } else {
                        idade.value--
                        verificaMaior()
                    }


                },
                modifier = Modifier.size(60.dp),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(Color(corFiap))
            ) {
                Text(text = "-", fontSize = 30.sp)
            }
            Spacer(modifier = Modifier.width(28.dp))
            Button(
                onClick = {
                    idade.value++
                    verificaMaior()
                },
                modifier = Modifier.size(60.dp),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(Color(corFiap))
            ) {
                Text(text = "+", fontSize = 30.sp)
            }
        }
        Spacer(modifier = Modifier.height(22.dp))
        Text(
            text = "${alertaTela.value}",
            fontSize = 20.sp,
            color = Color(0xFFAD1F4E),
            fontWeight = FontWeight.Bold,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        if (idade.value > 17) {
            Spacer(modifier = Modifier.height(10.dp)) // Adiciona um espaço antes da imagem
            retornaImagem()

        }

    }

    
}


@Preview(showSystemUi = true)
@Composable
private fun MeuComponentePreview() {
    MeuComponete()
}

//
//@Composable //essa bomba diferencia uma funcao normal de uma de estilizacao
//fun Greeting(name: String, modifier: Modifier = Modifier) {
//    Text(
//        text = "Eai $name",
//        color = Color.Red,
//        modifier = modifier
//    )
//
//}
//
//@Preview(showBackground = true, showSystemUi = true) // showSystemUi = true - pre visualiza a interface
//@Composable
//fun GreetingPreview() {
//    MinhaIdadeTheme {
//        Greeting("Dellano")
//    }
//}