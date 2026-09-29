package ru.tsaesar.anatomy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { AnatomyApp() }
    }
}

@Composable
private fun AnatomyApp() {
    var system by remember { mutableStateOf("Скелет") }
    var selected by remember { mutableStateOf("Череп") }
    var isolated by remember { mutableStateOf(false) }
    MaterialTheme {
        Scaffold(topBar = { TopAppBar(title = { Text("Anatomy RU 3D") }) }) { pad ->
            Column(Modifier.fillMaxSize().padding(pad).padding(12.dp)) {
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    FilterChip(selected = system == "Скелет", onClick = { system = "Скелет" }, label = { Text("Скелет") })
                    FilterChip(selected = system == "Мышцы", onClick = { system = "Мышцы" }, label = { Text("Мышцы") })
                }
                Spacer(Modifier.height(10.dp))
                Box(Modifier.fillMaxWidth().weight(1f).background(Color(0xFFF3F5F7), RoundedCornerShape(20.dp)), contentAlignment = Alignment.Center) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(if (isolated) "Изолировано: $selected" else "3D-сцена • $system", style = MaterialTheme.typography.headlineSmall)
                        Spacer(Modifier.height(8.dp))
                        Text("Вращение • масштаб • выбор структуры", color = Color.Gray)
                    }
                }
                Spacer(Modifier.height(10.dp))
                Text("Выбрано: $selected", style = MaterialTheme.typography.titleMedium)
                Text("$selected • ${if (selected == "Череп") "Cranium" else "Anatomical structure"}", color = Color.Gray)
                Spacer(Modifier.height(8.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Button(onClick = { isolated = !isolated }) { Text(if (isolated) "Показать всё" else "Изолировать") }
                    OutlinedButton(onClick = { selected = if (selected == "Череп") "Позвоночник" else "Череп" }) { Text("Выбрать") }
                }
            }
        }
    }
}
