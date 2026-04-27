package lopez.ermesto.thecheesery.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import lopez.ermesto.thecheesery.components.ProductForm
import lopez.ermesto.thecheesery.domain.Producto
import lopez.ermesto.thecheesery.ui.theme.Dusty_white
import lopez.ermesto.thecheesery.viewModel.rememberProductsViewModel

@Composable
fun AddProductScreen(innerPadding: PaddingValues) {

    val viewModel = rememberProductsViewModel()
    val products = viewModel.productsListState

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .background(Dusty_white),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        ProductForm { name, price, image, description, category  ->
            viewModel.saveProduct(
                Producto(name = name, price = price, image = image, description = description, category = category)
            )
        }

        Spacer(Modifier.height(20.dp))

        Text("Products", textAlign = TextAlign.Center, fontSize = 24.sp)

        Spacer(Modifier.height(10.dp))

        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(products) { product ->
                ProductItem(product)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AddProductScreenPreview() {

    val products = listOf(
        Producto(1, "Latte", 40f, null, "Café con leche", "hot"),
        Producto(2, "Muffin", 30f, null, "Chocolate", "sweets")
    )

    Column(
        modifier = Modifier.padding(20.dp)
    ) {

        ProductForm { _, _, _, _, _ -> }

        LazyColumn {
            items(products) {
                ProductItem(it)
            }
        }
    }
}