package lopez.ermesto.thecheesery.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import lopez.ermesto.thecheesery.R
import lopez.ermesto.thecheesery.data.coldDrinks
import lopez.ermesto.thecheesery.data.hotDrinks
import lopez.ermesto.thecheesery.data.salties
import lopez.ermesto.thecheesery.data.sweets
import lopez.ermesto.thecheesery.domain.Producto
import lopez.ermesto.thecheesery.ui.theme.*
import lopez.ermesto.thecheesery.viewmodel.CartViewModel

@Composable
fun ProductScreen(
    category: String,
    viewModel: CartViewModel,
    onBack: () -> Unit = {},
    onNavigateToCart: () -> Unit = {}
) {
    val (title, productList) = when (category.lowercase()) {
        "hot", "hot drinks" -> "Hot drinks" to hotDrinks
        "cold", "cold drinks" -> "Cold drinks" to coldDrinks
        "salties" -> "Salties" to salties
        "sweets" -> "Sweets" to sweets
        else -> category.replaceFirstChar { it.uppercase() } to emptyList()
    }

    ProductScreenContent(
        title = title,
        productList = productList,
        onAddToCart = { product ->
            viewModel.addProduct(product)
        },
        onBack = onBack,
        onNavigateToCart = onNavigateToCart
    )
}

@Composable
fun ProductScreenContent(
    title: String,
    productList: List<Producto>,
    onAddToCart: (Producto) -> Unit,
    onBack: () -> Unit = {},
    onNavigateToCart: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Dusty_white)
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
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
            IconButton(
                onClick = onNavigateToCart,
                modifier = Modifier
                    .size(44.dp)
                    .background(Less_Purple, CircleShape)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.cart),
                    contentDescription = "Ver carrito",
                    tint = Color.White,
                    modifier = Modifier.size(24.dp)
                )
            }
        }

        Text(
            text = title,
            fontFamily = HelloBunnyFontFamily,
            fontSize = 88.sp,
            color = Pinky,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(bottom = 16.dp)
        ) {
            items(productList) { product ->
                ProductItem(
                    product = product,
                    onAddToCart = { onAddToCart(product) }
                )
            }
        }
    }
}

@Composable
fun ProductItem(
    product: Producto,
    onAddToCart: () -> Unit,
    enabled: Boolean = true
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, RoundedCornerShape(12.dp))
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = product.image),
            contentDescription = product.name,
            modifier = Modifier
                .size(70.dp)
                .clip(CircleShape)
        )

        Spacer(modifier = Modifier.width(12.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = product.name,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = Less_Purple
            )

            product.description?.let { desc ->
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = desc,
                    fontSize = 12.sp,
                    color = Color.Gray,
                    maxLines = 3
                )
            }
        }

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = "$%.1f".format(product.price),
            fontWeight = FontWeight.Bold,
            color = Very_purple,
            fontSize = 18.sp
        )

        Spacer(modifier = Modifier.width(8.dp))

        FilledIconButton(
            onClick = onAddToCart,
            enabled = enabled,
            colors = IconButtonDefaults.filledIconButtonColors(
                containerColor = Very_purple,
                contentColor = Color.White,
                disabledContainerColor = DisabledGrey,
                disabledContentColor = PurpleGrey40
            ),
            modifier = Modifier.size(40.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.add_cart),
                contentDescription = "Agregar al carrito",
                modifier = Modifier.size(20.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProductScreenPreview() {
    ProductScreenContent(
        title = "Salties",
        productList = salties,
        onAddToCart = {}
    )
}
