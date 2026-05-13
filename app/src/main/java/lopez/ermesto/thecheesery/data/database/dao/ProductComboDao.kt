package lopez.ermesto.thecheesery.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update
import lopez.ermesto.thecheesery.data.database.entity.ProductComboEntity

@Dao
interface ProductComboDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProductCombo(productCombo: ProductComboEntity): Long

    @Update
    suspend fun updateProductCombo(productCombo: ProductComboEntity)

    @Delete
    suspend fun deleteProductCombo(productCombo: ProductComboEntity)
}
