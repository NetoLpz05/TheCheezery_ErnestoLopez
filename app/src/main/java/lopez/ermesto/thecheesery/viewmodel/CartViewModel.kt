package lopez.ermesto.thecheesery.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import lopez.ermesto.thecheesery.domain.Producto

class CartViewModel : ViewModel() {
    val cartItems = mutableStateListOf<Producto>()

    fun addProduct(product: Producto) {
        cartItems.add(product)
    }

    fun removeProduct(product: Producto) {
        cartItems.remove(product)
    }

    fun clearCart() {
        cartItems.clear()
    }

    val total: Float
        get() = cartItems.sumOf { it.price.toDouble() }.toFloat()
}
