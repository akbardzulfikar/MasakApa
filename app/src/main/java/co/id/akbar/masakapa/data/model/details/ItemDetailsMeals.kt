package co.id.akbar.masakapa.data.model.details

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class ItemDetailsMeals (
    @PrimaryKey
    @field:SerializedName("idMeal")
    val idMeal: Int,

    @field:SerializedName("strMeal")
    val strMeal: String,

    @field:SerializedName("strMealThumb")
    val strMealThumb: String,

    @field:SerializedName("strCategory")
    val strCategory: String,

    @field:SerializedName("strTags")
    val strTags: String,

    @field:SerializedName("strSource")
    val strSource: String,

    @field:SerializedName("strInstructions")
    val strInstructions: String,

    @field:SerializedName("strArea")
    val strArea: String,

    @field:SerializedName("strYoutube")
    val strYoutube: String,

    @field:SerializedName("strIngredient1")
    val strIngredient1: String,

    @field:SerializedName("strIngredient2")
    val strIngredient2: String,

    @field:SerializedName("strIngredient3")
    val strIngredient3: String,

    @field:SerializedName("strIngredient4")
    val strIngredient4: String,

    @field:SerializedName("strIngredient5")
    val strIngredient5: String,

    @field:SerializedName("strIngredient6")
    val strIngredient6: String,

    @field:SerializedName("strMeasure1")
    val strMeasure1: String,

    @field:SerializedName("strMeasure2")
    val strMeasure2: String,

    @field:SerializedName("strMeasure3")
    val strMeasure3: String,

    @field:SerializedName("strMeasure4")
    val strMeasure4: String,

    @field:SerializedName("strMeasure5")
    val strMeasure5: String,

    @field:SerializedName("strMeasure6")
    val strMeasure6: String
)