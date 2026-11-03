package lopez.ermesto.thecheesery.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import lopez.ermesto.thecheesery.R
import lopez.ermesto.thecheesery.domain.Producto
import lopez.ermesto.thecheesery.ui.theme.*
import lopez.ermesto.thecheesery.viewmodel.CartViewModel

@Composable
fun CartScreen(
    viewModel: CartViewModel,
    onBack: () -> Unit = {}
) {
    CartScreenContent(
        cartItems = viewModel.cartItems,
        total = viewModel.total,
        onRemoveProduct = { viewModel.removeProduct(it) },
        onBack = onBack
    )
}

@Composable
fun CartScreenContent(
    cartItems: List<Producto>,
    total: Float,
    onRemoveProduct: (Producto) -> Unit,
    onBack: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Dusty_white)
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
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
            text = "My Order",
            fontFamily = HelloBunnyFontFamily,
            fontSize = 70.sp,
            color = Pinky,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (cartItems.isEmpty()) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Add some products to your order!",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Purple_grey,
                    textAlign = TextAlign.Center
                )
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
            ) {
                items(cartItems) { product ->
                    CartItemRow(
                        product = product,
                        onRemove = { onRemoveProduct(product) }
                    )
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 24.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.Bottom
            ) {
                Text(
                    text = "Total ",
                    fontSize = 40.sp,
                    color = DarkGrey
                )
                Text(
                    text = "$%.1f".format(total),
                    fontSize = 50.sp,
                    fontWeight = FontWeight.Bold,
                    color = Less_Purple
                )
            }
        }
    }
}

@Composable
fun CartItemRow(
    product: Producto,
    onRemove: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = product.name,
            fontSize = 20.sp,
            color = DarkGrey,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.weight(1f)
        )

        Text(
            text = "$%.1f".format(product.price),
            fontSize = 20.sp,
            color = Very_purple,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 12.dp)
        )

        IconButton(
            onClick = onRemove,
            modifier = Modifier.size(36.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.delete),
                contentDescription = "Eliminar producto",
                tint = Pinky,
                modifier = Modifier.size(28.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CartScreenPreview() {
    val sampleProducts = listOf(
        Producto("Panini", 4.0f, R.drawable.ic_panini, ""),
        Producto("Cold Brew", 3.0f, R.drawable.coldbrew, ""),
        Producto("Matcha Latte", 4.0f, R.drawable.matcha, ""),
        Producto("Nachos", 7.0f, R.drawable.nachos, ""),
        Producto("Tiramisu", 6.0f, R.drawable.tiramisu, "")
    )
    val sampleTotal = sampleProducts.sumOf { it.price.toDouble() }.toFloat()

    CartScreenContent(
        cartItems = sampleProducts,
        total = sampleTotal,
        onRemoveProduct = {}
    )
}
