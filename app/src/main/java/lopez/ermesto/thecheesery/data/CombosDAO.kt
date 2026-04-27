package lopez.ermesto.thecheesery.data

import android.content.ContentValues
import lopez.ermesto.thecheesery.data.CheezeryContract.CombosEntry
import lopez.ermesto.thecheesery.data.CheezeryContract.ProductsComboEntry
import lopez.ermesto.thecheesery.data.CheezeryContract.ProductsEntry
import lopez.ermesto.thecheesery.domain.Combo
import lopez.ermesto.thecheesery.domain.Producto

class CombosDAO(private val dbHelper: DataBaseHelper) {
    fun insertCombo(combo: Combo, productIds: List<Int>): Long {
        val db = dbHelper.writableDatabase
        var comboId = -1L

        db.beginTransaction()
        try {
            val values = ContentValues().apply {
                put(CombosEntry.COLUMN_NAME, combo.name)
                put(CombosEntry.COLUMN_PRICE, combo.price)
            }
            comboId = db.insert(CombosEntry.TABLE_NAME, null, values)

            if (comboId != -1L) {
                productIds.forEach { productId ->
                    val relation = ContentValues().apply {
                        put(ProductsComboEntry.COLUMN_COMBO_ID, comboId)
                        put(ProductsComboEntry.COLUMN_PRODUCT_ID, productId)
                    }
                    db.insert(ProductsComboEntry.TABLE_NAME, null, relation)
                }
                db.setTransactionSuccessful()
            }
        } finally {
            db.endTransaction()
        }
        return comboId
    }

    fun getAllCombos(): List<Combo> {
        val db = dbHelper.readableDatabase
        val cursor = db.query(
            CombosEntry.TABLE_NAME,
            arrayOf(CombosEntry.COLUMN_ID, CombosEntry.COLUMN_NAME, CombosEntry.COLUMN_PRICE),
            null, null, null, null, null
        )
        val combos = mutableListOf<Combo>()
        with(cursor) {
            while (moveToNext()) {
                combos.add(
                    Combo(
                        id = getInt(getColumnIndexOrThrow(CombosEntry.COLUMN_ID)),
                        name = getString(getColumnIndexOrThrow(CombosEntry.COLUMN_NAME)),
                        price = getFloat(getColumnIndexOrThrow(CombosEntry.COLUMN_PRICE))))
            }
            close()
        }
        return combos
    }

    fun getComboWithProducts(comboId: Int): Combo? {
        val db = dbHelper.readableDatabase

        val comboCursor = db.query(
            CombosEntry.TABLE_NAME,
            arrayOf(
                CombosEntry.COLUMN_ID,
                CombosEntry.COLUMN_NAME,
                CombosEntry.COLUMN_PRICE),
            "${CombosEntry.COLUMN_ID} = ?",
            arrayOf(comboId.toString()),
            null, null, null
        )

        val combo = comboCursor.use {
            if (it.moveToFirst()) {
                Combo(
                    id = it.getInt(it.getColumnIndexOrThrow(CombosEntry.COLUMN_ID)),
                    name = it.getString(it.getColumnIndexOrThrow(CombosEntry.COLUMN_NAME)),
                    price = it.getFloat(it.getColumnIndexOrThrow(CombosEntry.COLUMN_PRICE))
                )
            } else null
        } ?: return null

        val productsCursor = db.rawQuery("""
            SELECT p.${ProductsEntry.COLUMN_ID}, p.${ProductsEntry.COLUMN_NAME},
                   p.${ProductsEntry.COLUMN_PRICE}, p.${ProductsEntry.COLUMN_IMAGE},
                   p.${ProductsEntry.COLUMN_DESCRIPTION}, p.${ProductsEntry.COLUMN_CATEGORY}
            FROM ${ProductsEntry.TABLE_NAME} p
            INNER JOIN ${ProductsComboEntry.TABLE_NAME} pc 
                ON p.${ProductsEntry.COLUMN_ID} = pc.${ProductsComboEntry.COLUMN_PRODUCT_ID}
            WHERE pc.${ProductsComboEntry.COLUMN_COMBO_ID} = ?
        """.trimIndent(), arrayOf(comboId.toString()))

        val products = mutableListOf<Producto>()
        with(productsCursor) {
            while (moveToNext()) {
                products.add(
                    Producto(
                        id = getInt(getColumnIndexOrThrow(ProductsEntry.COLUMN_ID)),
                        name = getString(getColumnIndexOrThrow(ProductsEntry.COLUMN_NAME)),
                        price = getFloat(getColumnIndexOrThrow(ProductsEntry.COLUMN_PRICE)),
                        image = getString(getColumnIndexOrThrow(ProductsEntry.COLUMN_IMAGE)),
                        description = getString(getColumnIndexOrThrow(ProductsEntry.COLUMN_DESCRIPTION)),
                        category = getString(getColumnIndexOrThrow(ProductsEntry.COLUMN_CATEGORY))))
            }
            close()
        }
        return combo.copy(products = products)
    }
}