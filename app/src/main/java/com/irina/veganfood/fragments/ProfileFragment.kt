package com.irina.veganfood.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.irina.veganfood.MainActivity
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

        activity!!.window.apply {
            (activity!! as AppCompatActivity).supportActionBar?.show()
            clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
            addFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN)
        }
        (activity!! as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)

        save.setOnClickListener {
            if(anyEmptyFields()){
                Toast.makeText(activity!!, R.string.empty_fields, Toast.LENGTH_SHORT).show()
                welcome.visibility = View.GONE
                acquaintance.visibility = View.VISIBLE
            } else {
                saveProfileInfo(
                    name.editText!!.text.toString(),
                    phone.editText!!.text.toString(),
                    address.editText!!.text.toString()
                )
                acquaintance.visibility = View.GONE
                welcome.visibility = View.VISIBLE
                Toast.makeText(activity!!, R.string.saved, Toast.LENGTH_SHORT).show()
                constraint.requestFocus()

            }
        }
    }

    private fun setupFields() {
        name.editText!!.setText(getProfileName())
        phone.editText!!.setText(getProfilePhone())
        address.editText!!.setText(getProfileAddress())
        if (getProfileAddress().isBlank()){
            welcome.visibility = View.GONE
            acquaintance.visibility = View.VISIBLE
        } else {
            acquaintance.visibility = View.GONE
            welcome.visibility = View.VISIBLE
        }
    }

    private fun anyEmptyFields() = listOf(name, phone, address).any { it.editText!!.text.isBlank() }
}