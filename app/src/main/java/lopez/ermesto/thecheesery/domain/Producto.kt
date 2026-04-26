package lopez.ermesto.thecheesery.domain

data class Producto(
    val id: Int = 0,
    val name: String,
    val price: Float,
    val image: String? = null,
    val description: String? = null
)