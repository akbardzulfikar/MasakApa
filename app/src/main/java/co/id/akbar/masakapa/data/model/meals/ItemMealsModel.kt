package co.id.akbar.masakapa.data.model.meals

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class ItemMealsModel (
    @PrimaryKey
    @field:SerializedName("idMeal")
    val idMeal: Int,

    @field:SerializedName("strMeal")
    val strMeal: String,

    @field:SerializedName("strMealThumb")
    val strMealThumb: String,

)