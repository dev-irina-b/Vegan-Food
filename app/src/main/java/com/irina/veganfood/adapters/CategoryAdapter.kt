package com.irina.veganfood.adapters
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.irina.veganfood.R
import com.irina.veganfood.fragments.MenuFragmentDirections
import com.irina.veganfood.models.Category
import kotlinx.android.synthetic.main.item_category.view.*

class CategoryAdapter : RecyclerView.Adapter<CategoryAdapter.ViewHolder>(){
    companion object {
        val items = listOf(
            Category("Salads", R.drawable.salads),
            Category("Hot meals", R.drawable.hot_meals),
            Category("Soups", R.drawable.soups),
            Category("Pasta", R.drawable.pasta),
            Category("Pizza and pitas", R.drawable.pizza_and_pitas),
            Category("Desserts", R.drawable.desserts),
            Category("Drinks", R.drawable.drinks)
        )
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_category,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = items[position].title

        Glide
            .with(holder.image.context)
            .load(items[position].drawable)
            .into(holder.image)
        holder.itemView.setOnClickListener {
            val action = MenuFragmentDirections.actionBottomMenuToMealsFragment(holder.adapterPosition)
            holder.itemView.findNavController().navigate(action)
        }
    }

    override fun getItemCount() = items.size
    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val title: TextView = view.title
        val image: ImageView = view.image
    }
}