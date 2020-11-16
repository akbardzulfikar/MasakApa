package co.id.akbar.masakapa.data.model.category

import com.google.gson.annotations.SerializedName

class CategoryModel (

    @field:SerializedName("categories")
    val categories: List<ItemCategoryModel>
)