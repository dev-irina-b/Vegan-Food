package com.irina.veganfood.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.irina.veganfood.MainActivity
import com.irina.veganfood.R
import com.irina.veganfood.adapters.MealsAdapter
import kotlinx.android.synthetic.main.fragment_menu.*

class MealsFragment : Fragment() {

    private val args: MealsFragmentArgs by navArgs()
    private val mainActivity by lazy { activity!! as MainActivity }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_meals, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

        mainActivity.setTitle(R.string.meals)
        mainActivity.supportActionBar?.setDisplayHomeAsUpEnabled(true)

        recycler.adapter = MealsAdapter(args.mealsType, context!!) {
            mainActivity.updateBadge()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == android.R.id.home)
            findNavController().popBackStack()

        return true
    }

}
