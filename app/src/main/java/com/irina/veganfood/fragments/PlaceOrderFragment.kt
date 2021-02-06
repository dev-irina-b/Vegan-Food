package com.irina.veganfood.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.irina.veganfood.R
import com.irina.veganfood.utils.getProfileAddress
import com.irina.veganfood.utils.getProfileName
import com.irina.veganfood.utils.getProfilePhone
import kotlinx.android.synthetic.main.fragment_profile.*

/**
 * A simple [Fragment] subclass.
 */
class PlaceOrderFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_place_order, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity!!.setTitle(R.string.delivery)

        setupFields()

    }

    private fun setupFields() {
        name.editText!!.setText(getProfileName())
        phone.editText!!.setText(getProfilePhone())
        address.editText!!.setText(getProfileAddress())
    }




}
