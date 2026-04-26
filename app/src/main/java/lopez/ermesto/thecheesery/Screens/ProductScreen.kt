package lopez.ermesto.thecheesery.Screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import lopez.ermesto.thecheesery.domain.Producto

@Composable
fun ProductScreen(products: List<Producto>){
    Column(Modifier.fillMaxWidth()) {
        Text(
            text = "Products",
            textAlign = TextAlign.Center,
            fontSize = 30.sp,
            modifier = Modifier.fillMaxWidth()
        )

        LazyColumn(Modifier.fillMaxWidth()) {
            items(products) { p ->
                Row{
                    Image(painter = painterResource(id = R.drawable.ic))

                    Column {
                        Text(text = p.name)
                        Text(text = p.description)
                    }
                }
            }

    }
}

@Preview(showBackground = true)
@Composable
fun ProductsScreenPreview(){
    ProductScreen(listOf(Producto()))
}