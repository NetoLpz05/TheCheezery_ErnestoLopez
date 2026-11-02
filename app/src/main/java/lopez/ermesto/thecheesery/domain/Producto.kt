package lopez.ermesto.thecheesery.domain

data class Producto(
    val name: String = "",
    val price: Float = 0F,
    val image: Int,
    val description: String? = null
)