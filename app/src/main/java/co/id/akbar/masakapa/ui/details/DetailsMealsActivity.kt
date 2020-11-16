package co.id.akbar.masakapa.ui.details

import android.os.Bundle
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import co.id.akbar.masakapa.R
import com.bumptech.glide.Glide
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import kotlinx.android.synthetic.main.activity_details_meals.*
import kotlinx.android.synthetic.main.content_detail_meals.*


class DetailsMealsActivity : AppCompatActivity() {

    private lateinit var detailMealsViewModel: DetailMealsViewModel
    private var mYoutube: String? = null

    companion object {
        const val EXTRA_DATA = "extra_data"
        const val EXTRA_MEAL = "extra_meal"
        private val TAG = DetailsMealsActivity::class.java.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_meals)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        detailMealsViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(
            DetailMealsViewModel::class.java
        )

        val detailMeal = intent.getIntExtra(EXTRA_DATA, 0)
        val detailMealName = intent.getStringExtra(EXTRA_MEAL)
        showData(detailMeal)
        supportActionBar?.title = detailMealName

        detailMealsViewModel.getMeals.observe(this, {
            Glide.with(this)
                .load(it[0].strMealThumb)
                .into(text_detail_image)
            textViewRandomMealArea.text = it[0].strArea
            textViewRandomCategory.text = it[0].strCategory
            textViewIngredient1.text = it[0].strIngredient1
            textViewIngredient2.text = it[0].strIngredient2
            textViewIngredient3.text = it[0].strIngredient3
            textViewIngredient4.text = it[0].strIngredient4
            textViewIngredient5.text = it[0].strIngredient5
            textViewIngredient6.text = it[0].strIngredient6
            textViewIns.text = it[0].strInstructions
            textViewRandomSourceText.text = it[0].strSource
            textViewMeasure1.text = it[0].strMeasure1
            textViewMeasure2.text = it[0].strMeasure2
            textViewMeasure3.text = it[0].strMeasure3
            textViewMeasure4.text = it[0].strMeasure4
            textViewMeasure5.text = it[0].strMeasure5
            textViewMeasure6.text = it[0].strMeasure6
            mYoutube = getVideoId(it[0].strYoutube)
        })

        val youTubePlayerView: YouTubePlayerView = findViewById(R.id.youtube_player_view)
        lifecycle.addObserver(youTubePlayerView)

        youTubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(@NonNull youTubePlayer: YouTubePlayer) {
                mYoutube?.let { youTubePlayer.loadVideo(it, 0f) }
            }
        })
    }

    private fun showData(detail: Int?) {
        detail?.let {
            detailMealsViewModel.loadMeals(applicationContext, it.toString())
        }
    }

    fun getVideoId(watchLink: String): String? {
        return watchLink.substring(watchLink.length - 11)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}