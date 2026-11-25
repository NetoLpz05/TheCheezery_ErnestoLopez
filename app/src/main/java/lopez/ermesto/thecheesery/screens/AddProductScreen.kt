package lopez.ermesto.thecheesery.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import lopez.ermesto.thecheesery.R
import lopez.ermesto.thecheesery.ui.theme.*
import lopez.ermesto.thecheesery.viewmodel.CheezeryViewModel

@Composable
fun AddProductScreen(
    cheezeryViewModel: CheezeryViewModel,
    onBack: () -> Unit = {}
) {
    var name by remember { mutableStateOf("") }
    var priceStr by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var isCombo by remember { mutableStateOf(false) }
    var successMessage by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Dusty_white)
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onBack) {
                Text(
                    text = "←",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = Less_Purple
                )
            }
        }

        Text(
            text = "Add New Item",
            fontFamily = HelloBunnyFontFamily,
            fontSize = 60.sp,
            color = Pinky,
            modifier = Modifier.padding(vertical = 8.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Name") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = priceStr,
            onValueChange = { priceStr = it },
            label = { Text("Price") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Description") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Checkbox(checked = isCombo, onCheckedChange = { isCombo = it })
            Text(text = "Is this a Combo?")
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                val price = priceStr.toFloatOrNull() ?: 0f
                if (name.isNotBlank()) {
                    if (isCombo) {
                        cheezeryViewModel.addCombo(name, price, description, emptyList())
                    } else {
                        cheezeryViewModel.addProduct(name, price, R.drawable.chailatte, description)
                    }
                    successMessage = true
                    name = ""
                    priceStr = ""
                    description = ""
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = Very_purple),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Save Item", color = Color.White, fontSize = 16.sp)
        }

        if (successMessage) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Item added successfully!",
                color = Color(0xFF4CAF50),
                fontWeight = FontWeight.Bold
            )
        }
    }
}
