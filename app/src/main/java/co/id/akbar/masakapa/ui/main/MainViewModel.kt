package co.id.akbar.masakapa.ui.main

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import co.id.akbar.masakapa.data.db.AppDatabase
import co.id.akbar.masakapa.data.db.CategoryDao
import co.id.akbar.masakapa.data.model.category.ItemCategoryModel
import co.id.akbar.masakapa.data.network.RfConfig
import com.google.gson.Gson
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainViewModel : ViewModel() {
    private val listCategory : MutableLiveData<List<ItemCategoryModel>> = MutableLiveData()
    private var db: AppDatabase? = null
    private var categoryDao: CategoryDao? = null
    private var compositeDisposable: CompositeDisposable = CompositeDisposable()

    val getCategory : LiveData<List<ItemCategoryModel>> = listCategory

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

    fun initDatabase(context: Context) {
        db = AppDatabase.getAppDataBase(context)
        categoryDao = db?.categoryDao()
    }

    fun loadCategory(context: Context){
        compositeDisposable.add(
            RfConfig.getRetrofit().getData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                insertData(it.categories)
            }, { error ->
                getData()
                Log.e("MainViewModel", error.message!!)
                Toast.makeText(context, "Please check your internet connection", Toast.LENGTH_SHORT).show()
            })
        )
    }

    private fun insertData(listCategoryModel: List<ItemCategoryModel>) {
        Observable.fromCallable {
            with(categoryDao) {
                this?.delete()
                this?.insertAll(listCategoryModel)
            }
            db?.categoryDao()?.getAll()
        }.doOnNext { list ->
            this.listCategory.postValue(list)
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }

    private fun getData() {
        Observable.fromCallable {
            db?.categoryDao()?.getAll()
        }.doOnNext { list ->
            listCategory.postValue(list)
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }

    fun searchTitle(strCategory: String) {
        Observable.fromCallable {
            db?.categoryDao()?.searchTitle(strCategory)
        }.doOnNext { list ->
            Log.d("MainViewModel", Gson().toJson(list))
            listCategory.postValue(list)
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }

    fun sortAsc() {
        Observable.fromCallable {
            db?.categoryDao()?.getCategorySortAsc()
        }.doOnNext { list ->
            Log.d("MainViewModel", Gson().toJson(list))
            listCategory.postValue(list)
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }

    fun sortDesc() {
        Observable.fromCallable {
            db?.categoryDao()?.getCategorySortDsc()
        }.doOnNext { list ->
            Log.d("MainViewModel", Gson().toJson(list))
            listCategory.postValue(list)
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }
}