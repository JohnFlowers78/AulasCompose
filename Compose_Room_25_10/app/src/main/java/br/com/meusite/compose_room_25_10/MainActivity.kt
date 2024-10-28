package br.com.meusite.compose_room_25_10.Data

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.meusite.compose_room_25_10.Data.ui.theme.Compose_Room_25_10Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Compose_Room_25_10Theme {
//                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    Greeting(
//                        name = "Android",
//                        modifier = Modifier.padding(innerPadding)
//                    )
//                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun InputNovoLivro(){

    var nome by remember { mutableStateOf("") }
    var ano by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.padding(20.dp)
    ) {
        Text("Novo Livro: ", fontSize = 16.sp)

        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            OutlinedTextField(
                value = "Nome",
                onValueChange = { nome = it },
                label = { Text("Nome: ") },
                modifier = Modifier.weight(1f)
            )
            OutlinedTextField(
                value = "Ano",
                onValueChange = { ano = it },
                label = { Text("Ano: ") },
                modifier = Modifier.weight(1f)
            )
        }
        Button(onClick = {
            corroutineScope.launch
            val novoLivro = Livro(0, nome, ano)
            val contexto = LocalContext.current
            AppDatabase.getDatabase(contexto).livroDao().addLivro(novoLivro)
        }) {
            Text("Criar Livro")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Compose_Room_25_10Theme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            shape = RoundedCornerShape(10.dp),
            shadowElevation = 10.dp,
            border = BorderStroke(0.1.dp, Color.LightGray)
        ) {
            var livros = listOf(
                Livro(0, "O Senhor dos Aneis", "1954"),
                Livro(1, "1984", "1959"),
                Livro(2, "2000", "2000"),
                Livro(3, "Como Fazer AMigos e Influenciar Pessoas", "1500")
            )
            ListaDeLivros(livros)
        }
    }
}

@Composable
fun ListaDeLivros(livros: List<Livro>){
    LazyColumn {
        items(livros){ livro ->
            CardLivro(livro)
        }
    }
}

@Composable
fun CardLivro(livro: Livro) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier.padding(6.dp)
        ) {
            Text("ID: ${livro.id}", style = MaterialTheme.typography.bodySmall)
            Text("Nome: ${livro.nome}", style = MaterialTheme.typography.headlineLarge)
            Text("Ano: ${livro.ano}", style = MaterialTheme.typography.bodyMedium)   // Dar CTRL + Click (mouse) é possível tudo disponível
        }
    }
}
