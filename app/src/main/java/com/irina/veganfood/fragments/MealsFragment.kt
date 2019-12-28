package com.irina.veganfood.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.irina.veganfood.MainActivity
import com.irina.veganfood.R
import com.irina.veganfood.adapters.MealsAdapter
import com.irina.veganfood.utils.getAllOrderedMeals
import com.irina.veganfood.utils.getPrice
import kotlinx.android.synthetic.main.fragment_menu.*

class MealsFragment : Fragment() {

    private val args: MealsFragmentArgs by navArgs()
    private val mainActivity by lazy { activity!! as MainActivity }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_meals, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainActivity.setTitle(R.string.meals)

        recycler.adapter = MealsAdapter(args.mealsType, context!!) {
            var price = 0
            mainActivity.getAllOrderedMeals().forEach {
                price += it.amount * it.meal.price.getPrice()
            }
            mainActivity.badge.text = "$$price"
            mainActivity.badge.visibility = if(price > 0) View.VISIBLE else View.GONE
        }
    }

}
