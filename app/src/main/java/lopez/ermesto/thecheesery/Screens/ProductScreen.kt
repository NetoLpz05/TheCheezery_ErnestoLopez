package lopez.ermesto.thecheesery.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import lopez.ermesto.thecheesery.R
import lopez.ermesto.thecheesery.domain.Producto
import lopez.ermesto.thecheesery.ui.theme.Dusty_white
import lopez.ermesto.thecheesery.ui.theme.Very_purple
import lopez.ermesto.thecheesery.viewModel.rememberProductsViewModel

@Composable
fun ProductScreen(category: String) {

    val viewModel = rememberProductsViewModel()

    LaunchedEffect(category) {
        viewModel.loadProductsByCategory(category)
    }

    val products = viewModel.categoryProductsState

    Column(modifier = Modifier.fillMaxSize().background(Dusty_white).padding(16.dp)) {

        Text(
            text = "Category: $category",
            fontSize = 24.sp
        )

        Spacer(modifier = Modifier.height(10.dp))

        LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            items(products) { product ->
                ProductItem(product)
            }
        }
    }
}

@Composable
fun ProductItem(product: Producto) {

    Row(
        modifier = Modifier.fillMaxWidth()
            .background(Color.White, RoundedCornerShape(8.dp)).padding(12.dp),
        verticalAlignment = Alignment.CenterVertically) {

        Image(painter = painterResource(id = R.drawable.muffin),
            contentDescription = product.name,
            modifier = Modifier.size(70.dp).clip(RoundedCornerShape(8.dp)))

        Spacer(modifier = Modifier.width(12.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(text = product.name, fontWeight = FontWeight.Bold, fontSize = 16.sp)

            product.description?.let {
                Text(text = it, fontSize = 12.sp, color = Color.Gray, maxLines = 2)
            }
        }

        Text(text = "$%.2f".format(product.price), fontWeight = FontWeight.Bold,
            color = Very_purple, fontSize = 14.sp)
    }
}

@Preview(showBackground = true)
@Composable
fun ProductScreenPreview() {
    val products = listOf(
        Producto(1, "Latte", 40f, "", "Café con leche", "hot"),
        Producto(2, "Cappuccino", 45f, "", "Espumoso", "hot"),
        Producto(3, "Muffin", 30f, "", "Chocolate", "sweets")
    )

    Column(modifier = Modifier.fillMaxSize().background(Dusty_white).padding(16.dp)) {

        Text("Category: hot", fontSize = 24.sp)
        Spacer(modifier = Modifier.height(10.dp))

        LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            items(products.filter { it.category == "hot" }) {
                ProductItem(it)
            }
        }
    }
}