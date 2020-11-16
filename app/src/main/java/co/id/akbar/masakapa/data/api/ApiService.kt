package co.id.akbar.masakapa.data.api

import co.id.akbar.masakapa.data.model.category.CategoryModel
import co.id.akbar.masakapa.data.model.details.DetailsMealsModel
import co.id.akbar.masakapa.data.model.meals.MealsModel
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("categories.php")
    fun getData() : Flowable<CategoryModel>

    @GET("filter.php")
    fun getMeals(@Query("c") value: String) : Flowable<MealsModel>

    @GET("search.php")
    fun searchMeals(@Query("s") value: String) : Flowable<MealsModel>

    @GET("lookup.php")
    fun getDetailsMeals(@Query("i") detail: String) : Flowable<DetailsMealsModel>
}