package co.id.akbar.masakapa.data.model.category

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class ItemCategoryModel (
    @PrimaryKey
    @ColumnInfo(name = "idCategory")
    @field:SerializedName("idCategory")
    val idCategory: Int,

    @ColumnInfo(name = "strCategory")
    @field:SerializedName("strCategory")
    val strCategory: String,

    @ColumnInfo(name = "strCategoryThumb")
    @field:SerializedName("strCategoryThumb")
    val strCategoryThumb: String,

    @ColumnInfo(name = "strCategoryDescription")
    @field:SerializedName("strCategoryDescription")
    val strCategoryDescription: String
)