package com.irina.veganfood.adapters
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.irina.veganfood.R
import com.irina.veganfood.models.Meal
import com.irina.veganfood.utils.getSPE
import kotlinx.android.synthetic.main.item_meal.view.*

class MealsAdapter(private val mealType: Int, private val context: Context, private val callback: () -> Unit) : RecyclerView.Adapter<MealsAdapter.ViewHolder>(){

    companion object {
        val salads = listOf(
            Meal("Green salad", "$5", "150g", "Cucumbers, green beans, spinach, fried brussels sprouts, celery, sesame oil and bread", "100" , "2", "10", "15", R.drawable.green_salad_with_bread),
            Meal("Tortilla chips with salsa", "$3", "100g", "Tortilla chips, vegetable oil, avocado, lime, red onion, coriander, plum tomatoes, garlic, chipotle tabasco", "766", "15", "38", "74", R.drawable.tortilla_chips_with_salsa)
        )
        val hotMeals = listOf(
            Meal("Falafel with fresh vegetables", "$4", "200g", "Chickpeas, parsley, garlic, coriander, olive oil, onion, cumin, sesame seeds", "581", "10", "30", "50", R.drawable.falafel_with_fresh_vegetables),
            Meal("Hummus with bread", "$6", "200g", "Chickpeas, lemon, garlic, cumin, sesame and bread", "250", "16", "10", "44", R.drawable.hummus_with_bread),
            Meal("Fresh vegetables with sweet potato fries", "$3", "200g", "Sweet potatoes, soybean oil, black pepper, paprika, garlic powder, radish, coriander, pickles", "357", "9.6", "15.4", "84", R.drawable.fresh_vegetables_with_sweet_potato_fries),
            Meal("Baked carrots on a chickpeas salad", "$4", "300g", "Carrot, chickpeas, olive oil, black pepper, sweet peas,  lemon, sugar, kalamata olives, sunflower seeds,  parsley", "529", "17", "32", "48", R.drawable.baked_carrots_on_a_chickpeas_salad),
            Meal("Cous cous with vegetables", "$2", "250g", "Olive oil, red onion, bell pepper, carrot, garlic, paprika, coriander, peas, couscous, broccoli", "472", "17", "10", "77", R.drawable.cous_cous_with_vegetables_in_a_pan),
            Meal("Oatmeal with chia seeds banana and walnuts", "$2", "250g", "Oats, chia seeds, banana, walnuts, oat milk", "340", "6", "6", "30", R.drawable.oatmeal_with_chia_seeds_banana_and_walnuts),
            Meal("Banana oatmeal with blueberries, chocolate and apple", "$5", "250g", "Oats, oat milk, banana, blueberries, chocolate and apple, vanilla", "309", "6.3", "4.5", "32", R.drawable.banana_oatmeal_with_blueberries_chocolate_and_apple),
            Meal("Cereals bowl with frozen raspberries and nuts", "$5", "250g", "Almonds, walnut, peanut, hazelnut, almond milk, raspberries", "330", "15", "20", "80", R.drawable.cereals_bowl_with_frozen_raspberries_and_nuts)

        )

        val soups = listOf(
            Meal("Pumpkin soup with onions", "$3", "250g", "Coconut Oil, onion, garlic, ginger, cayenne pepper, pumpkin", "317", "24.6", "26.9", "27.8", R.drawable.pumpkin_soup_with_onions),
            Meal("Autumn pumpkin soup", "$3", "250g", "Pumpkin, maple syrup, nutmeg, cumin, and coriander, cauliflower, almond milk", "221", "7", "14", "25", R.drawable.autumn_pumpkin_soup),
            Meal("Carrot soup", "$2", "250g", "Onion, chia seeds, garlic, carrot, peppers, parsley, balsamic", "68", "2", "4", "15", R.drawable.carrot_soup),
            Meal("Mixed carrot soup_with veggies", "$2", "250g", "Olive oil, onion, celery, garlic, ginger, lime, cumin, turmeric, coriander, carrot, potato", "161", "5", "3", "32", R.drawable.mixed_carrot_soup_with_veggies),
            Meal("Creamy mushroom soup", "$4", "250g", "Olive oil, onion, garlic, oregano, basil, white button mushroom, soy sauce, coconut milk", "271", "9", "21", "15", R.drawable.creamy_mushroom_soup)


        )

        val pasta = listOf(
            Meal("Noodles with vegetables", "$4", "250g", "Vegetable oil, red bell pepper, carrot, broccoli, onion, sesame seeds, soy sauce, chili-garlic sauce, wheat noodles", "405", "17", "13", "58", R.drawable.noodles_with_vegetables),
            Meal("Rice noodles with roasted vegetables", "$4", "250g", "Sesame oil, onion, red pepper, snow peas, curry powder, soy sauce, lime, garlic, coconut sugar, rice noodles, vegan cheese, tofu", "342", "7", "15", "49", R.drawable.rice_noodles_with_roasted_vegetables),
            Meal("Noodles", "$4", "250g", "Olive oil, onion, red pepper, green pepper, carrot, mushroom, ginger, soy sauce, sesame seeds, udon noodles", "334", "14", "6", "65", R.drawable.noodles)

        )

        val pizza = listOf(
            Meal("Chia flour pizza", "$6", "250g", "Hemp oil, chickpea flour, chia seeds, turmeric, coconut oil, oregano, red chili, tomatoes, vegan cheese, mushrooms, sweet bite peppers, basil", "", "", "", "", R.drawable.chia_flour_pizza),
            Meal("Pizza", "$6", "250g", "Dough, tomato sauce, vegan cheese, garlic oil, oregano, red pepper, basil", "350", "10", "5", "64", R.drawable.pizza),
            Meal("Margherita", "$6", "250g", "Dough, vegan cheese, cherry tomatoes, spices, tomato sauce, garlic oil", "217", "7", "9", "25", R.drawable.pizza_margherita),
            Meal("Healthy wraps", "$6", "250g", "Pillowy garlic herb flatbreads, cinnamon-roasted sweet potatoes, peppery arugula, crispy baked chickpeas, toasted pumpkin seeds, dried cranberries, garlic-dill sauce", "464", "12", "10", "83", R.drawable.healthy_vegan_wraps),
            Meal("Vegetables rolls", "$6", "250g", "Cucumber, onion, green pepper, dill, lettuce, carrot, celery, tortillas", "347", "12", "8", "55", R.drawable.vegetables_rolls)
        )

        val desserts = listOf(
            Meal("Smoothie with fruits", "$2", "250g", "Banana, strawberry, mango, blueberries, kiwi, almond milk", "367", "9", "2", "109", R.drawable.smoothie_with_fruits),
            Meal("Pistachio croissant", "$1", "250g", "Dough, almond milk, vanilla, cornstarch, pistachios, sugar, vegan pastry cream", "442", "9", "26", "46", R.drawable.pistachio_croissant),
            Meal("Homemade apple pie", "$4", "250g", " Flour, sugar, vegan butter, apples, lemon, coconut oil, cinnamon", "470", "12", "16", "80", R.drawable.homemade_apple_pie),
            Meal("Cinnamon roll", "$3", "250g", "Vegan butter, almond milk, sugar, flour, cinnamon, ", "243, ", "5", "9", "34", R.drawable.cinnamon_roll),
            Meal("Muffin with poppy seed", "$2", "250g", "Flour, poppy seeds, sugar, canola oil, soy milk, vegan butter, vanilla", "200", "3", "6", "33", R.drawable.muffin_with_poppy_seed),
            Meal("Macarons", "$5", "250g", "Aquafaba, almonds, sugar, vegan red food coloring, vanilla, vegan butter, ", "250", "13", "6", "44", R.drawable.macarons)

        )

        val drinks = listOf(
            Meal("Homemade cucumber lemonade", "$1", "0.25l", "Water, sugar, cucumber, lemon", "141", "2.5", "0.6", "45.5", R.drawable.homemade_cucumber_lemonade),
            Meal("Fresh grapefruit juice", "$1", "0.25l", "Grapefruit", "76", "1", "0.2", "18", R.drawable.fresh_grapefruit_juice),
            Meal("Hot cinnamon tea", "$1", "0.25l", "Hot cinnamon spice tea, orange", "10", "0", "0", "0.3", R.drawable.hot_cinnamon_tea),
            Meal("Black tea", "$1", "0.25l", "Indian black tea", "10", "0", "0", "0.3", R.drawable.black_tea),
            Meal("Latte with almond milk", "$1", "0.25l", " Strong brewed coffee, almond milk, vanilla", "47", "0.5", "1", "7", R.drawable.cortado_coffee)


        )
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_meal, 
                parent, 
                false
            )
        )

    private fun setMealAmount(category: Int, meal: Int, amount: Int) {
        context.getSPE().putInt("Meal_${category}_$meal", amount).apply()
        callback()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getCurrentMeals()[position].apply {
            holder.title.text = title
            holder.price.text = price
            holder.weight.text = weight
            holder.content.text = content
            holder.kcal.text = kcal
            holder.protein.text = protein
            holder.fat.text = fat
            holder.carbs.text = carbs

            Glide
                .with(holder.image.context)
                .load(drawable)
                .into(holder.image)

            holder.info.setOnClickListener {
                holder.infoLayout.visibility =
                    if (holder.infoLayout.visibility == View.GONE)
                        View.VISIBLE
                    else
                        View.GONE
            }

            holder.btnAdd.setOnClickListener {
                if(holder.number.visibility == View.GONE) {
                    holder.minus.visibility = View.VISIBLE
                    holder.number.visibility = View.VISIBLE
                    holder.plus.visibility = View.VISIBLE
                    holder.btnText.visibility = View.GONE

                    setMealAmount(mealType, holder.adapterPosition, 1)
                }
            }

            holder.minus.setOnClickListener {
                val currentNumber = holder.number.text.toString().toInt()
                if(currentNumber > 1){
                    holder.number.text = (currentNumber-1).toString()
                    setMealAmount(mealType, holder.adapterPosition, currentNumber-1)
                } else {
                    holder.minus.visibility = View.GONE
                    holder.number.visibility = View.GONE
                    holder.plus.visibility = View.GONE
                    holder.btnText.visibility = View.VISIBLE
                    setMealAmount(mealType, holder.adapterPosition, 0)
                }
            }

            holder.plus.setOnClickListener {
                val currentNumber = holder.number.text.toString().toInt()
                if(currentNumber < 100){
                    holder.number.text = (currentNumber+1).toString()
                    setMealAmount(mealType, holder.adapterPosition, currentNumber+1)
                }
            }
        }
    }

    override fun getItemCount() = getCurrentMeals().size

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val title: TextView = view.title
        val price: TextView = view.price
        val weight: TextView = view.weight
        val content: TextView = view.content
        val kcal: TextView = view.kcal
        val protein: TextView = view.protein
        val fat: TextView = view.fat
        val carbs: TextView = view.carbs
        val image: ImageView = view.image
        val info: ImageView = view.info
        val infoLayout: ConstraintLayout = view.infoLayout
        val btnAdd: View = view.btnAdd
        val btnText: TextView = view.btnText
        val minus: ImageView = view.minus
        val plus: ImageView = view.plus
        val number: TextView = view.number
    }

    private fun getCurrentMeals() = when(mealType) {
        Meal.SALADS -> salads
        Meal.HOT_MEALS -> hotMeals
        Meal.SOUPS -> soups
        Meal.PASTA -> pasta
        Meal.PIZZA -> pizza
        Meal.DESSERTS -> desserts
        else -> drinks
    }
}