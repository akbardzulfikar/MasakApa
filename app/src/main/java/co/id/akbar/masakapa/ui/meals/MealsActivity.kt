package co.id.akbar.masakapa.ui.meals

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import co.id.akbar.masakapa.R
import co.id.akbar.masakapa.ui.details.DetailsMealsActivity
import co.id.akbar.masakapa.ui.meals.adapter.MealsAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MealsActivity : AppCompatActivity() {

    private lateinit var mealsViewModel: MealsViewModel
    private var value = ""

    companion object {
        const val EXTRA_NAME = "extra_name"
        private val TAG = MealsActivity::class.java.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meals)

        mealsViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(
            MealsViewModel::class.java
        )

        val detailName = intent.getStringExtra(EXTRA_NAME)
        showName(detailName)

        setSupportActionBar(findViewById(R.id.toolbar))
        searcUser.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    if (query.isNotEmpty()) {
                        mealsViewModel.searchNames(applicationContext, query)
                    } else {
                        Log.d(TAG, "query is empty")
                        mealsViewModel.loadMeals(applicationContext, value)
                    }
                } else {
                    mealsViewModel.loadMeals(applicationContext, value)
                }
                return false
            }
        })
        setSupportActionBar(findViewById(R.id.toolbar))

        val mealsAdapter = MealsAdapter()
        mealsViewModel.loadMeals(applicationContext, value)
        mealsViewModel.getMeals.observe(this, {
            mealsAdapter.setData(applicationContext, it)
            rvUser.adapter = mealsAdapter
        })
        rvUser.layoutManager = GridLayoutManager(this, 2)

        swipeRefresh.setOnRefreshListener {
            mealsViewModel.loadMeals(applicationContext, value)
            swipeRefresh.isRefreshing = false
        }

        mealsAdapter.onItemClick = {
            Log.d(TAG, "query is not null ${it.idMeal}")
            val intentDetails = Intent(this, DetailsMealsActivity::class.java)
            intentDetails.putExtra(DetailsMealsActivity.EXTRA_DATA, it.idMeal)
            intentDetails.putExtra(DetailsMealsActivity.EXTRA_MEAL, it.strMeal)
            startActivity(intentDetails)
        }
    }

    private fun showName(name: String?) {
        name?.let {
            value = it
            mealsViewModel.loadMeals(applicationContext, value)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}