package com.irina.veganfood.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.irina.veganfood.R
import com.irina.veganfood.adapters.FragmentPagerAdapter
import kotlinx.android.synthetic.main.fragment_promo.*

class PromoFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? { return inflater.inflate(R.layout.fragment_promo, container, false) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity!!.window.apply {
            (activity!! as AppCompatActivity).supportActionBar?.hide()
            addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
            clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN)
        }

        pager.adapter = FragmentPagerAdapter(childFragmentManager)
        pager.offscreenPageLimit = 4

        promoDots.setupWithViewPager(pager)
    }
}
