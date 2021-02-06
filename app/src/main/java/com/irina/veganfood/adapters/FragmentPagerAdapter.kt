package com.irina.veganfood.adapters

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.irina.veganfood.fragments.*

class FragmentPagerAdapter(manager: FragmentManager) : FragmentPagerAdapter(manager)   {

    override fun getItem(position: Int) = when(position) {
        0 -> VeganDayPromoFragment()
        1 -> BirthdayPromoFragment()
        2 -> AnimalDayPromoFragment()
        3 -> YogaDayPromoFragment()
        else -> EarthDayPromoFragment()
    }

    override fun getCount() = 5
}