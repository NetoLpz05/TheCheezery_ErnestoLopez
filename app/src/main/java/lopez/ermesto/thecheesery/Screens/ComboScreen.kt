package lopez.ermesto.thecheesery.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*
import lopez.ermesto.thecheesery.domain.Combo
import lopez.ermesto.thecheesery.ui.theme.Dusty_white
import lopez.ermesto.thecheesery.ui.theme.Very_purple
import lopez.ermesto.thecheesery.viewModel.rememberProductsViewModel

@Composable
fun CombosScreen() {

    val viewModel = rememberProductsViewModel()

    LaunchedEffect(Unit) {
        viewModel.getAllCombos()
    }

    val combos = viewModel.combosListState

    Column(
        modifier = Modifier.fillMaxSize().background(Dusty_white).padding(16.dp)
    ) {
        Text("Combos", fontSize = 24.sp)

        Spacer(modifier = Modifier.height(10.dp))

        LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            items(combos) { combo ->
                val comboWithProducts = remember(combo.id) {
                    viewModel.getComboWithProducts(combo.id)
                }
                ComboItem(comboWithProducts ?: combo)
            }
        }
    }
}

@Composable
fun ComboItem(combo: Combo) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, RoundedCornerShape(8.dp))
            .padding(12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(combo.name, fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Text("$%.2f".format(combo.price), fontWeight = FontWeight.Bold, color = Very_purple)
        }

        if (combo.products.isNotEmpty()) {
            Spacer(Modifier.height(8.dp))
            HorizontalDivider(color = Color.LightGray, thickness = 0.5.dp)
            Spacer(Modifier.height(6.dp))

            Text("Includes:", fontSize = 12.sp, color = Color.Gray)

            Spacer(Modifier.height(4.dp))

            combo.products.forEach { product ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("• ${product.name}", fontSize = 12.sp)
                    Text("$%.2f".format(product.price), fontSize = 12.sp, color = Very_purple)
                }
            }
        }
    }
}