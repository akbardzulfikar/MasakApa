package co.id.akbar.masakapa.ui.details

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import co.id.akbar.masakapa.data.model.details.ItemDetailsMeals
import co.id.akbar.masakapa.data.network.RfConfig
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class DetailMealsViewModel : ViewModel(){
    private val listDetailMeal : MutableLiveData<List<ItemDetailsMeals>> = MutableLiveData()
    private var compositeDisposable: CompositeDisposable = CompositeDisposable()

    val getMeals : LiveData<List<ItemDetailsMeals>> = listDetailMeal

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

    fun loadMeals(context: Context, detail: String) {
        compositeDisposable.add(
            RfConfig.getRetrofit().getDetailsMeals(detail)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    listDetailMeal.postValue(it.detailMeals)
                }, { error ->
                    Log.e("MainViewModel", error.message!!)
                    Toast.makeText(context, "Please check your internet connection", Toast.LENGTH_SHORT).show()
                })
        )
    }
}