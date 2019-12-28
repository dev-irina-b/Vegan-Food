package com.irina.veganfood.utils

import android.content.Context
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationMenuView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.irina.veganfood.R
import com.irina.veganfood.adapters.MealsAdapter
import com.irina.veganfood.models.CartItem

fun Context.getSP(): SharedPreferences = getSharedPreferences(getString(R.string.shared_prefs_name), Context.MODE_PRIVATE)

fun Context.getSPE(): SharedPreferences.Editor = getSP().edit()

fun Fragment.getSP() = context!!.getSP()

fun Fragment.getSPE(): SharedPreferences.Editor = context!!.getSPE()

fun Fragment.saveProfileInfo(name: String, phone: String, address: String) {
    getSPE()
        .putString("name", name)
        .putString("phone", phone)
        .putString("address", address)
        .apply()
}

fun Fragment.getProfileName() = getSP().getString("name", "")
fun Fragment.getProfilePhone() = getSP().getString("phone", "")
fun Fragment.getProfileAddress() = getSP().getString("address", "")
fun Context.getMealAmount(category: Int, meal: Int) = getSP().getInt("Meal_${category}_$meal", 0)
fun Context.deleteAllCartItems() {
    val editor = getSPE()
    MealsAdapter.salads.forEachIndexed { index, meal ->
        editor.remove("Meal_1_$index")
    }
    MealsAdapter.hotMeals.forEachIndexed { index, meal ->
        editor.remove("Meal_2_$index")
    }
    MealsAdapter.soups.forEachIndexed { index, meal ->
        editor.remove("Meal_3_$index")
    }
    MealsAdapter.pasta.forEachIndexed { index, meal ->
        editor.remove("Meal_4_$index")
    }
    MealsAdapter.pizza.forEachIndexed { index, meal ->
        editor.remove("Meal_5_$index")
    }
    MealsAdapter.desserts.forEachIndexed { index, meal ->
        editor.remove("Meal_6_$index")
    }
    MealsAdapter.drinks.forEachIndexed { index, meal ->
        editor.remove("Meal_7_$index")
    }

    editor.apply()
}
fun Context.getAllOrderedMeals(): MutableList<CartItem> {
    val result = mutableListOf<CartItem>()

    MealsAdapter.salads.forEachIndexed { index, meal ->
        val amount = getMealAmount(0, index)
        if(amount > 0)
            result.add(CartItem(meal, amount))
    }

    MealsAdapter.hotMeals.forEachIndexed { index, meal ->
        val amount = getMealAmount(1, index)
        if(amount > 0)
            result.add(CartItem(meal, amount))
    }

    MealsAdapter.soups.forEachIndexed { index, meal ->
        val amount = getMealAmount(2, index)
        if(amount > 0)
            result.add(CartItem(meal, amount))
    }

    MealsAdapter.pasta.forEachIndexed { index, meal ->
        val amount = getMealAmount(3, index)
        if(amount > 0)
            result.add(CartItem(meal, amount))
    }

    MealsAdapter.pizza.forEachIndexed { index, meal ->
        val amount = getMealAmount(4, index)
        if(amount > 0)
            result.add(CartItem(meal, amount))
    }

    MealsAdapter.desserts.forEachIndexed { index, meal ->
        val amount = getMealAmount(5, index)
        if(amount > 0)
            result.add(CartItem(meal, amount))
    }

    MealsAdapter.drinks.forEachIndexed { index, meal ->
        val amount = getMealAmount(6, index)
        if(amount > 0)
            result.add(CartItem(meal, amount))
    }

    return result
}

fun String.getPrice() = substring(1).toInt()

fun BottomNavigationView.addBadge(itemPosition: Int, @LayoutRes badgeLayout: Int): View {
    val menuView = getChildAt(0) as BottomNavigationMenuView
    val itemView = menuView.getChildAt(itemPosition) as BottomNavigationItemView
    val view = LayoutInflater.from(context).inflate(badgeLayout, menuView, false)
    itemView.addView(view)
    return view
}
fun BottomNavigationView.getBadgeView(itemPosition: Int, @IdRes viewId: Int): View {
    val menuView = getChildAt(0) as BottomNavigationMenuView
    val itemView = menuView.getChildAt(itemPosition) as BottomNavigationItemView
    val badge = itemView.getChildAt(itemView.childCount-1) as ViewGroup
    return badge.findViewById(viewId)
}