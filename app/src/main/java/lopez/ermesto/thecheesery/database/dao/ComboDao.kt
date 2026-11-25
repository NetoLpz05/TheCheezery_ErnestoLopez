package lopez.ermesto.thecheesery.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import lopez.ermesto.thecheesery.database.entity.ComboEntity
import lopez.ermesto.thecheesery.database.relation.ComboWithProducts

@Dao
interface ComboDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCombo(combo: ComboEntity): Long

    @Update
    suspend fun updateCombo(combo: ComboEntity)

    @Delete
    suspend fun deleteCombo(combo: ComboEntity)

    @Query("SELECT * FROM Combos")
    fun getAllCombos(): Flow<List<ComboEntity>>

    @Transaction
    @Query("SELECT * FROM Combos")
    fun getCombosWithProducts(): Flow<List<ComboWithProducts>>
}
