package com.irina.veganfood.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.irina.veganfood.MainActivity
import com.irina.veganfood.R
import com.irina.veganfood.adapters.CategoryAdapter
import kotlinx.android.synthetic.main.fragment_menu.*

/**
 * A simple [Fragment] subclass.
 */
class MenuFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_menu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity!!.setTitle(R.string.menu)
        (activity!! as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)

        activity!!.window.apply {
            (activity!! as AppCompatActivity).supportActionBar?.show()
            clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
            addFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN)
        }

        recycler.adapter = CategoryAdapter()
    }


}
