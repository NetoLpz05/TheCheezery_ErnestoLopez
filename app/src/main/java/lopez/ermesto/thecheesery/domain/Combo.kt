package lopez.ermesto.thecheesery.domain

data class Combo(
    val id: Int = 0,
    val name: String,
    val price: Float,
    val products: List<Producto> = emptyList()
)