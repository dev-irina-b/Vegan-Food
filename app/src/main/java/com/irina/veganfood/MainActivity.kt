package com.irina.veganfood

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import com.irina.veganfood.utils.addBadge
import com.irina.veganfood.utils.getAllOrderedMeals
import com.irina.veganfood.utils.getBadgeView
import com.irina.veganfood.utils.getPrice
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val badge by lazy { navigation.getBadgeView(2, R.id.textView) as TextView }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupNavigation()

        navigation.addBadge(2, R.layout.badge)
        updateBadge()
    }

    private fun setupNavigation() {
        val navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        navigation.setupWithNavController(navController)
    }

    fun updateBadge() {
        var price = 0
        getAllOrderedMeals().forEach {
            price += it.amount * it.meal.price.getPrice()
        }
        badge.text = "$$price"
        badge.visibility = if(price > 0) View.VISIBLE else View.GONE
    }

}