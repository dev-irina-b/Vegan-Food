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
        const val SALADS = 1
        const val HOT_MEALS = 2
        const val SOUPS = 3
        const val PASTA = 4
        const val PIZZA = 5
        const val DESSERTS = 6
        const val DRINKS = 7
    }
}