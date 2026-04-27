package lopez.ermesto.thecheesery.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.*
import lopez.ermesto.thecheesery.domain.Producto
import lopez.ermesto.thecheesery.ui.theme.Dusty_white
import lopez.ermesto.thecheesery.viewModel.rememberProductsViewModel

@Composable
fun AddComboScreen(innerPadding: PaddingValues) {

    val viewModel = rememberProductsViewModel()
    val allProducts = viewModel.productsListState
    var comboName by remember { mutableStateOf("") }
    var comboPrice by remember { mutableStateOf("") }
    val selectedProductIds = remember { mutableStateSetOf<Int>() }

    Column(
        modifier = Modifier.fillMaxSize().padding(innerPadding).background(Dusty_white).padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text("Create Combo", fontSize = 28.sp)

        Spacer(Modifier.height(16.dp))

        OutlinedTextField(value = comboName, onValueChange = { comboName = it }, label = { Text("Combo name") }, modifier = Modifier.fillMaxWidth())

        Spacer(Modifier.height(8.dp))

        OutlinedTextField(value = comboPrice, onValueChange = { comboPrice = it }, label = { Text("Price") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal), modifier = Modifier.fillMaxWidth())

        Spacer(Modifier.height(16.dp))

        Text("Select products:", fontSize = 18.sp)

        Spacer(Modifier.height(8.dp))

        LazyColumn(modifier = Modifier.weight(1f)) {
            items(allProducts) { product ->
                ProductCheckboxItem(
                    product = product,
                    isSelected = selectedProductIds.contains(product.id),
                    onToggle = {
                        if (selectedProductIds.contains(product.id))
                            selectedProductIds.remove(product.id)
                        else
                            selectedProductIds.add(product.id)
                    }
                )
            }
        }

        Spacer(Modifier.height(12.dp))

        Button(
            onClick = {
                viewModel.saveCombo(
                    name = comboName,
                    price = comboPrice.toFloatOrNull() ?: 0f,
                    productIds = selectedProductIds.toList()
                )
                comboName = ""
                comboPrice = ""
                selectedProductIds.clear()
            },
            enabled = comboName.isNotBlank() && selectedProductIds.isNotEmpty(),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Save combo")
        }
    }
}

@Composable
fun ProductCheckboxItem(product: Producto, isSelected: Boolean, onToggle: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(checked = isSelected, onCheckedChange = { onToggle() })
        Spacer(Modifier.width(8.dp))
        Column {
            Text(product.name, fontSize = 16.sp)
            Text("$%.2f".format(product.price), fontSize = 12.sp)
        }
    }
}