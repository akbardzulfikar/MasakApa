package co.id.akbar.masakapa.ui.meals.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import co.id.akbar.masakapa.R
import co.id.akbar.masakapa.data.model.meals.ItemMealsModel
import com.bumptech.glide.Glide

class MealsAdapter : RecyclerView.Adapter<MealsAdapter.ViewHolder>() {

    private lateinit var context: Context
    private var mealsList = ArrayList<ItemMealsModel>()
    var onItemClick: ((ItemMealsModel) -> Unit)? = null

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val tvStrCategory = view.findViewById<TextView>(R.id.tv_item_title)
        private val tvStrThumb = view.findViewById<ImageView>(R.id.iv_item_image)

        fun bind(meals: ItemMealsModel) {
            tvStrCategory.text = meals.strMeal
            Glide.with(itemView.context)
                .load(meals.strMealThumb)
                .into(tvStrThumb)
        }

        init {
            view.setOnClickListener {
                onItemClick?.invoke(mealsList[adapterPosition])
            }
        }
    }

    fun setData(context: Context, userList: List<ItemMealsModel>?) {
        if (userList == null) return
        this.context = context
        this.mealsList.clear()
        this.mealsList.addAll(userList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_category, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return mealsList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(mealsList[position])
    }
}