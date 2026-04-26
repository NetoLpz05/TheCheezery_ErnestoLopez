package lopez.ermesto.thecheesery.data

import android.content.ContentValues
import lopez.ermesto.thecheesery.domain.Producto
import lopez.ermesto.thecheesery.data.CheezeryContract.ProductsEntry

public class ProductDAO(private val dbHelper: DataBaseHelper) {
    fun InsertProduct(producto: Producto): Long {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(ProductsEntry.COLUMN_ID, producto.id)
            put(ProductsEntry.COLUMN_NAME, producto.name)
            put(ProductsEntry.COLUMN_PRICE, producto.price)
            put(ProductsEntry.COLUMN_DESCRIPTION, producto.description)
            put(ProductsEntry.COLUMN_IMAGE, producto.image)
        }
        return db.insert(ProductsEntry.TABLE_NAME, null, values)
    }

    fun getAllProducts(): List<Producto>{
        val db = dbHelper.readableDatabase
        val cursor = db.query(ProductsEntry.TABLE_NAME, arrayOf(ProductsEntry.COLUMN_ID,
            ProductsEntry.TABLE_NAME, ProductsEntry.COLUMN_PRICE, ProductsEntry.COLUMN_DESCRIPTION, ProductsEntry.COLUMN_IMAGE),
            null, null, null, null, null)
        val products = mutableListOf<Producto>()
        with (cursor){
            while (moveToNext()){
                val id = getInt(getColumnIndexOrThrow(ProductsEntry.COLUMN_ID))
                val name = getString(getColumnIndexOrThrow(ProductsEntry.COLUMN_NAME))
                val price = getFloat(getColumnIndexOrThrow(ProductsEntry.COLUMN_PRICE))
                val description = getString(getColumnIndexOrThrow(ProductsEntry.COLUMN_DESCRIPTION))
                val image = getString(getColumnIndexOrThrow(ProductsEntry.COLUMN_IMAGE))
            }
            cursor.close()
        }
        return products
    }

    fun getProductById(productId: Int): Producto?{
        val db = dbHelper.readableDatabase
        val cursor = db.query(ProductsEntry.TABLE_NAME,
            arrayOf(ProductsEntry.COLUMN_ID, ProductsEntry.COLUMN_NAME, ProductsEntry.COLUMN_PRICE,
                ProductsEntry.COLUMN_DESCRIPTION, ProductsEntry.COLUMN_IMAGE),
            "${ProductsEntry.COLUMN_ID} = ? ", arrayOf(productId.toString()),
            null, null, null
        )
        val product: Producto? = cursor.use {
            if (it.moveToFirst()) {
                val id = it.getInt(it.getColumnIndexOrThrow(ProductsEntry.COLUMN_ID))
                val name = it.getString(it.getColumnIndexOrThrow(ProductsEntry.COLUMN_NAME))
                val price = it.getFloat(it.getColumnIndexOrThrow(ProductsEntry.COLUMN_PRICE))
                val description = it.getString(it.getColumnIndexOrThrow(ProductsEntry.COLUMN_DESCRIPTION))
                val image = it.getString(it.getColumnIndexOrThrow(ProductsEntry.COLUMN_IMAGE))
                Producto(id, name, price, image, description)
            } else {
                null
            }
        }
        return product
    }
}