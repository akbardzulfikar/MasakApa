package co.id.akbar.masakapa.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import co.id.akbar.masakapa.data.model.category.ItemCategoryModel

@Dao
interface CategoryDao {
    @Query("SELECT * FROM itemcategorymodel")
    fun getAll(): List<ItemCategoryModel>

    @Insert
    fun insertAll(users: List<ItemCategoryModel>)

    @Query("DELETE FROM itemcategorymodel")
    fun delete()

    @Query("SELECT * FROM itemcategorymodel WHERE strCategory LIKE '%' || :strCategory || '%'")
    fun searchTitle(strCategory: String): List<ItemCategoryModel>

    @Query("SELECT * from itemcategorymodel ORDER BY strCategory ASC")
    fun getCategorySortAsc(): List<ItemCategoryModel>

    @Query("SELECT * from itemcategorymodel ORDER BY strCategory DESC")
    fun getCategorySortDsc(): List<ItemCategoryModel>
}