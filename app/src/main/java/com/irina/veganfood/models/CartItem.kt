package com.irina.veganfood.models

data class CartItem(
    val meal: Meal,
    var amount: Int
)