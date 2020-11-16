package co.id.akbar.masakapa.data.model.details

import com.google.gson.annotations.SerializedName

class DetailsMealsModel (

    @field:SerializedName("meals")
    val detailMeals: List<ItemDetailsMeals>
)