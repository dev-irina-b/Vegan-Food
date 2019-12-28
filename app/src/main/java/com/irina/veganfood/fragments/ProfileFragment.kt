package com.irina.veganfood.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.irina.veganfood.R
import com.irina.veganfood.utils.getProfileAddress
import com.irina.veganfood.utils.getProfileName
import com.irina.veganfood.utils.getProfilePhone
import com.irina.veganfood.utils.saveProfileInfo
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity!!.setTitle(R.string.profile)

        setupFields()

        save.setOnClickListener {
            if(isFieldsEmpty())
                Toast.makeText(activity!!, R.string.empty_fields, Toast.LENGTH_SHORT).show()
            else {
                saveProfileInfo(
                    name.editText!!.text.toString(),
                    phone.editText!!.text.toString(),
                    address.editText!!.text.toString()
                )
                Toast.makeText(activity!!, R.string.saved, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setupFields() {
        name.editText!!.setText(getProfileName())
        phone.editText!!.setText(getProfilePhone())
        address.editText!!.setText(getProfileAddress())
    }

    private fun isFieldsEmpty() = listOf(name, phone, address).all { it.editText!!.text.isBlank() }
}