package com.example.myapplication

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Função da sebenta (secção 5.1) para procurar uma substring na lista de strings
fun searchStrings(listaStrings: List<String>, searchTxt: String): List<String> {
    return listaStrings.filter {
        it.contains(searchTxt, ignoreCase = true)
    }
}

// Ecrã 1: ADD — Adiciona "numero nome pontuacao" à lista de Strings
@Composable
fun Ecra01(listaStrings: SnapshotStateList<String>) {
    val numero = rememberSaveable { mutableStateOf("") }
    val nome = rememberSaveable { mutableStateOf("") }
    val pontuacao = rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
        ) {
            Text(text = "NÚMERO", modifier = Modifier.weight(0.35f))
            TextField(
                value = numero.value,
                onValueChange = { numero.value = it },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true,
                modifier = Modifier.weight(0.65f)
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
        ) {
            Text(text = "NOME", modifier = Modifier.weight(0.35f))
            TextField(
                value = nome.value,
                onValueChange = { nome.value = it },
                singleLine = true,
                modifier = Modifier.weight(0.65f)
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
        ) {
            Text(text = "PONTUAÇÃO", modifier = Modifier.weight(0.35f))
            TextField(
                value = pontuacao.value,
                onValueChange = { pontuacao.value = it },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true,
                modifier = Modifier.weight(0.65f)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            if (numero.value.isNotBlank() && nome.value.isNotBlank() && pontuacao.value.toFloatOrNull() != null) {
                // Guarda na ordem: número, nome e pontuação
                listaStrings.add("${numero.value.trim()} ${nome.value.trim()} ${pontuacao.value.trim()}")
                numero.value = ""
                nome.value = ""
                pontuacao.value = ""
            }
        }) {
            Text("ADICIONAR")
        }
    }
}

// Ecrã 2: FIND — Procura por Número ou Nome na lista de Strings
@Composable
fun Ecra02(listaStrings: SnapshotStateList<String>) {
    val numeroPesquisa = rememberSaveable { mutableStateOf("") }
    val nomePesquisa = rememberSaveable { mutableStateOf("") }
    val resultados = remember { mutableStateListOf<String>() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "NÚMERO", modifier = Modifier.weight(0.3f))
            TextField(
                value = numeroPesquisa.value,
                onValueChange = { numeroPesquisa.value = it },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true,
                modifier = Modifier.weight(0.7f)
            )
        }

        Text(text = "ou", modifier = Modifier.padding(vertical = 4.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "NOME", modifier = Modifier.weight(0.3f))
            TextField(
                value = nomePesquisa.value,
                onValueChange = { nomePesquisa.value = it },
                singleLine = true,
                modifier = Modifier.weight(0.7f)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            resultados.clear()
            val numTxt = numeroPesquisa.value.trim()
            val nomeTxt = nomePesquisa.value.trim()

            if (numTxt.isNotEmpty()) {
                resultados.addAll(searchStrings(listaStrings, numTxt))
            } else if (nomeTxt.isNotEmpty()) {
                resultados.addAll(searchStrings(listaStrings, nomeTxt))
            }
        }) {
            Text("PROCURAR")
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .border(BorderStroke(1.dp, Color.Gray))
                .padding(8.dp)
        ) {
            items(resultados) { item ->
                Text(
                    text = item,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(vertical = 4.dp)
                )
            }
        }
    }
}

// Ecrã 3: MIN/MAX — Extrai a pontuação (último valor da String) para achar o mínimo e o máximo
@Composable
fun Ecra03(listaStrings: SnapshotStateList<String>) {
    val minRegisto = listaStrings.minByOrNull {
        it.substringAfterLast(" ").toFloatOrNull() ?: Float.MAX_VALUE
    }
    val maxRegisto = listaStrings.maxByOrNull {
        it.substringAfterLast(" ").toFloatOrNull() ?: Float.MIN_VALUE
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (listaStrings.isEmpty()) {
            Text("Sem registos na lista.", fontSize = 18.sp)
        } else {
            Text("MÍNIMO:", fontWeight = FontWeight.Bold, fontSize = 20.sp)
            Text(
                text = minRegisto ?: "",
                fontSize = 18.sp,
                modifier = Modifier.padding(bottom = 24.dp)
            )

            Text("MÁXIMO:", fontWeight = FontWeight.Bold, fontSize = 20.sp)
            Text(
                text = maxRegisto ?: "",
                fontSize = 18.sp
            )
        }
    }
}

// Ecrã 4: LIST — Usa a função StringList da sebenta para listar todas as Strings
@Composable
fun Ecra04(listaStrings: SnapshotStateList<String>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(listaStrings) { item ->
            Text(
                text = item,
                fontSize = 18.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            )
        }
    }
}