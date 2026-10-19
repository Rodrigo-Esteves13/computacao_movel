package com.example.myapplication

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Ecra01(name: MutableState<String>, sharedResult: MutableState<String>) {
    // Variável para guardar o que o utilizador escreve na caixa da conta
    val contaInput = rememberSaveable { mutableStateOf("") }

    Column(modifier = Modifier.padding(all = 16.dp)) {
        Text(
            text = "Hello ${name.value}!",
            fontSize = 30.sp,
            modifier = Modifier.padding(16.dp)
        )

        // Linha para o Nome
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)) {
            Text(text = "Nome:", modifier = Modifier.width(60.dp))
            TextField(
                value = name.value,
                onValueChange = { name.value = it },
                modifier = Modifier.weight(1f)
            )
        }

        // Linha para a Conta
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)) {
            Text(text = "Conta:", modifier = Modifier.width(60.dp))
            TextField(
                value = contaInput.value,
                onValueChange = { contaInput.value = it },
                modifier = Modifier.weight(1f),
                placeholder = { Text("Ex: 5+5") }
            )
        }
        Button(
            onClick = {
                sharedResult.value = calcularConta(contaInput.value)
            },
            modifier = Modifier.align(Alignment.CenterHorizontally).padding(16.dp)
        ) {
            Text("Calcular e Enviar")
        }

        if (sharedResult.value.isNotEmpty()) {
            Text(
                text = "Resultado pronto: ${sharedResult.value} (Vai ao ecrã 2)",
                color = Color.Green,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
    }
}

@Composable
fun Ecra02(name: MutableState<String>, sharedResult: MutableState<String>) {
    Column(modifier = Modifier.fillMaxSize().wrapContentSize(Alignment.Center)) {
        Text(
            text = "Nome recebido: ${name.value}",
            fontSize = 24.sp,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Text(
            text = "Resultado do Cálculo: ${if (sharedResult.value.isEmpty()) "Nenhum cálculo feito" else sharedResult.value}",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Blue,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Text(
            text = stringResource(id = R.string.ecra02),
            fontWeight = FontWeight.Bold, color = Color.Gray,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center, fontSize = 18.sp
        )
    }
}

@Composable
fun Ecra03() {
    Column(modifier = Modifier.fillMaxSize().wrapContentSize(Alignment.Center)) {
        Text(
            text = stringResource(id = R.string.ecra03),
            fontWeight = FontWeight.Bold, color = Color.Gray,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center, fontSize = 18.sp
        )
    }
}

@Composable
fun Ecra04() {
    Column(modifier = Modifier.fillMaxSize().wrapContentSize(Alignment.Center)) {
        Text(
            text = stringResource(id = R.string.ecra04),
            fontWeight = FontWeight.Bold, color = Color.Gray,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center, fontSize = 18.sp
        )
    }
}