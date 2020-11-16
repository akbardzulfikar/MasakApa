package co.id.akbar.masakapa.data.model.meals

import com.google.gson.annotations.SerializedName

class MealsModel (

    @field:SerializedName("meals")
    val meals: List<ItemMealsModel>
)