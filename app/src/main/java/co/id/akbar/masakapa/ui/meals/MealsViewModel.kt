package co.id.akbar.masakapa.ui.meals

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import co.id.akbar.masakapa.data.model.meals.ItemMealsModel
import co.id.akbar.masakapa.data.network.RfConfig
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MealsViewModel : ViewModel() {
    private val listMeal : MutableLiveData<List<ItemMealsModel>> = MutableLiveData()
    private var compositeDisposable: CompositeDisposable = CompositeDisposable()

    val getMeals : LiveData<List<ItemMealsModel>> = listMeal

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

    fun loadMeals(context: Context, value: String) {
        compositeDisposable.add(
            RfConfig.getRetrofit().getMeals(value)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    listMeal.postValue(it.meals)
                }, { error ->
                    Log.e("MainViewModel", error.message!!)
                    Toast.makeText(context, "Please check your internet connection", Toast.LENGTH_SHORT).show()
                })
        )
    }

    fun searchNames(context: Context, value: String) {
        compositeDisposable.add(
            RfConfig.getRetrofit().searchMeals(value)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    listMeal.postValue(it.meals)
                }, { error ->
                    Log.e("MainViewModel", error.message!!)
                    Toast.makeText(context, "Please check your internet connection", Toast.LENGTH_SHORT).show()
                })
        )
    }
}