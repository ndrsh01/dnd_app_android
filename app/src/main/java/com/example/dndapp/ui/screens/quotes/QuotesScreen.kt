package com.example.dndapp.ui.screens.quotes

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FormatQuote
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.dndapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuotesScreen() {
    val quotes = listOf(
        "Приключения ждут тех, кто готов их искать.",
        "В темноте подземелий скрыты величайшие сокровища и опасности.",
        "Дружба, закаленная в бою, крепче стали.",
        "Магия - это не просто сила, это искусство.",
        "Каждый герой начинается с первого шага.",
        "В мире фантазии нет границ для воображения.",
        "Драконы могут быть побеждены, но легенды живут вечно.",
        "Мудрость приходит с опытом, а опыт - с ошибками.",
        "В единстве сила, в разделении слабость.",
        "Приключения - это не путешествие, это состояние души."
    )
    
    var currentQuoteIndex by remember { mutableStateOf(0) }
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(R.string.nav_quotes),
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )
        
        Spacer(modifier = Modifier.height(32.dp))
        
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    Icons.Default.FormatQuote,
                    contentDescription = null,
                    modifier = Modifier.size(48.dp),
                    tint = MaterialTheme.colorScheme.onPrimaryContainer
                )
                
                Spacer(modifier = Modifier.height(16.dp))
                
                Text(
                    text = quotes[currentQuoteIndex],
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }
        }
        
        Spacer(modifier = Modifier.height(24.dp))
        
        Button(
            onClick = {
                currentQuoteIndex = (currentQuoteIndex + 1) % quotes.size
            }
        ) {
            Text("Следующая цитата")
        }
    }
}

