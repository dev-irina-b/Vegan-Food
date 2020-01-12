package com.irina.veganfood.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.irina.veganfood.R
import com.irina.veganfood.utils.getAllOrderedMeals
import com.irina.veganfood.utils.getPrice
import com.irina.veganfood.utils.getSPE
import kotlinx.android.synthetic.main.item_cart.view.*

class CartAdapter(private val context: Context, private val badgeCallback: () -> Unit): RecyclerView.Adapter<CartAdapter.ViewHolder>() {

    private var items = context.getAllOrderedMeals()

    fun updateItems() {
        items = context.getAllOrderedMeals()
    }

    private fun setMealAmount(category: Int, meal: Int, amount: Int) {
        context.getSPE().putInt("Meal_${category}_$meal", amount).apply()
        badgeCallback()
    }

    override fun onBindViewHolder(holder:ViewHolder, position: Int) {
        items[position].apply {
            holder.title.text = meal.title
            holder.price.text = "$" + meal.price.getPrice()*amount
            holder.amount.text = amount.toString()
            val radius = context.resources.getDimensionPixelSize(R.dimen.glide_radius)
            Glide
                .with(holder.image.context)
                .load(meal.drawable)
                .apply(RequestOptions()
                    .transform(CenterCrop(), RoundedCorners(radius)))
                .into(holder.image)

            holder.minus.setOnClickListener {
                val currentNumber = amount
                if(currentNumber > 1){
                    holder.number.text = (currentNumber-1).toString()
                    setMealAmount(meal.category, meal.positionInCategory, currentNumber-1)
                    amount--
                    holder.price.text = "$" + (amount*meal.price.getPrice())
                } else {
                    val index = items.indexOf(this)
                    items.removeAt(index)
                    notifyItemRemoved(index)
                    setMealAmount(meal.category, meal.positionInCategory, 0)
                    amount = 0
                }
            }

            holder.plus.setOnClickListener {
                val currentNumber = amount
                if(currentNumber < 100){
                    holder.number.text = (currentNumber+1).toString()
                    setMealAmount(meal.category, meal.positionInCategory, currentNumber+1)
                    amount++
                    holder.price.text = "$" + (amount*meal.price.getPrice())
                }
            }

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
        val minus: ImageView = view.minus
        val plus: ImageView = view.plus
        val number: TextView = view.number
    }


}