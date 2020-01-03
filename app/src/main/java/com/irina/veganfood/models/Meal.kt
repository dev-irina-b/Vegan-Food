package com.irina.veganfood.models

import androidx.annotation.DrawableRes

data class Meal(
    val title: String,
    val price: String,
    val weight: String,
    val content: String,
    val kcal: String,
    val protein: String,
    val fat: String,
    val carbs: String,
    @DrawableRes
    val drawable: Int
)
{
    companion object {
        const val SALADS = 0
        const val HOT_MEALS = 1
        const val SOUPS = 2
        const val PASTA = 3
        const val PIZZA = 4
        const val DESSERTS = 5
        const val DRINKS = 6
    }

    var category = -1
}