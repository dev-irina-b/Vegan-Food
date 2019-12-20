package com.irina.veganfood.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.irina.veganfood.R
import com.irina.veganfood.adapters.MealsAdapter
import kotlinx.android.synthetic.main.fragment_menu.*

class MealsFragment : Fragment() {

    private val args: MealsFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_meals, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recycler.adapter = MealsAdapter(args.mealsType)
    }

}
