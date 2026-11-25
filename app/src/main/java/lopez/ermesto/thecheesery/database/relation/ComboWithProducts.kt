package lopez.ermesto.thecheesery.database.relation

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import lopez.ermesto.thecheesery.database.entity.ComboEntity
import lopez.ermesto.thecheesery.database.entity.ProductEntity
import lopez.ermesto.thecheesery.database.entity.ProductComboEntity

data class ComboWithProducts(
    @Embedded
    val combo: ComboEntity,
    @Relation(
        parentColumn = "idCombo",
        entityColumn = "idProduct",
        associateBy = Junction(
            value = ProductComboEntity::class,
            parentColumn = "idCombo",
            entityColumn = "idProduct"
        )
    )
    val products: List<ProductEntity>
)
