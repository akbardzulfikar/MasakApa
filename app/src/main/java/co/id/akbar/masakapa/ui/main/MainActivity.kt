package co.id.akbar.masakapa.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import co.id.akbar.masakapa.R
import co.id.akbar.masakapa.ui.main.adapter.CategoryAdapter
import co.id.akbar.masakapa.ui.meals.MealsActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(
                MainViewModel::class.java
        )

        setSupportActionBar(findViewById(R.id.toolbar))
        searcUser.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    mainViewModel.searchTitle(query)
                } else {
                    mainViewModel.loadCategory(applicationContext)
                }
                return false
            }
        })
        setSupportActionBar(findViewById(R.id.toolbar))

        val categoryAdapter = CategoryAdapter()
        mainViewModel.initDatabase(applicationContext)
        mainViewModel.loadCategory(applicationContext)
        mainViewModel.getCategory.observe(this, {
            categoryAdapter.setData(applicationContext, it)
            rvUser.adapter = categoryAdapter
        })
        rvUser.layoutManager = GridLayoutManager(this, 2)

        swipeRefresh.setOnRefreshListener {
            mainViewModel.loadCategory(applicationContext)
            swipeRefresh.isRefreshing = false
        }

        categoryAdapter.onItemClick = {
            val intent = Intent(this, MealsActivity::class.java)
            intent.putExtra(MealsActivity.EXTRA_NAME, it.strCategory)
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        setMode(item.itemId)
        return super.onOptionsItemSelected(item)
    }

    private fun setMode(selectedMode: Int) {
        when (selectedMode) {
            R.id.action_sort_ascending -> {
                mainViewModel.sortAsc()
            }
            R.id.action_sort_descending -> {
                mainViewModel.sortDesc()
            }
        }
    }

}