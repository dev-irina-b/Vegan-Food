package com.irina.veganfood.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.irina.veganfood.R
import com.irina.veganfood.utils.getAllOrderedMeals
import kotlinx.android.synthetic.main.item_cart.view.*

class CartAdapter(private val context: Context): RecyclerView.Adapter<CartAdapter.ViewHolder>() {

    private var items = context.getAllOrderedMeals()

    fun updateItems() {
        items = context.getAllOrderedMeals()
    }


    override fun onBindViewHolder(holder:ViewHolder, position: Int) {
        items[position].apply {
            holder.title.text = meal.title
            holder.price.text = meal.price
            holder.amount.text = amount.toString()
            Glide
                .with(holder.image.context)
                .load(meal.drawable)
                .into(holder.image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_cart,
                parent,
                false
            )
        )

    override fun getItemCount() = items.size

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val image: ImageView = view.image
        val title: TextView = view.title
        val price: TextView = view.price
        val amount: TextView = view.number


    }
}