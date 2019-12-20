package com.irina.veganfood.models

import androidx.annotation.DrawableRes

data class Category(
    val title: String,
    @DrawableRes
    val drawable: Int
)