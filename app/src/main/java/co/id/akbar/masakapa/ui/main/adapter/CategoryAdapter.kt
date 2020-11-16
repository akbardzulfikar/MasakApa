package co.id.akbar.masakapa.ui.main.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import co.id.akbar.masakapa.R
import co.id.akbar.masakapa.data.model.category.ItemCategoryModel
import com.bumptech.glide.Glide

class CategoryAdapter : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    private lateinit var context: Context
    private var userList = ArrayList<ItemCategoryModel>()
    var onItemClick: ((ItemCategoryModel) -> Unit)? = null

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val tvStrCategory = view.findViewById<TextView>(R.id.tv_item_title)
        private val tvStrDescription = view.findViewById<TextView>(R.id.tv_item_subtitle)
        private val tvStrThumb = view.findViewById<ImageView>(R.id.iv_item_image)

        fun bind(category: ItemCategoryModel) {
            tvStrCategory.text = category.strCategory
            tvStrDescription.text = category.strCategoryDescription
            Glide.with(itemView.context)
                .load(category.strCategoryThumb)
                .into(tvStrThumb)
        }

        init {
            view.setOnClickListener {
                onItemClick?.invoke(userList[adapterPosition])
            }
        }
    }

    fun setData(context: Context, userList: List<ItemCategoryModel>?) {
        if (userList == null) return
        this.context = context
        this.userList.clear()
        this.userList.addAll(userList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_category, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(userList[position])
    }
}