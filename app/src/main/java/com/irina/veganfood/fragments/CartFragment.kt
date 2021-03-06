package com.irina.veganfood.fragments

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.irina.veganfood.MainActivity
import com.irina.veganfood.R
import com.irina.veganfood.adapters.CartAdapter
import com.irina.veganfood.utils.deleteAllCartItems
import kotlinx.android.synthetic.main.fragment_cart.*

class CartFragment : Fragment() {

    private val mainActivity by lazy { activity!! as MainActivity }
    private val adapter by lazy { CartAdapter(context!!) { mainActivity.updateBadge() } }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_cart, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity!!.setTitle(R.string.cart)
        mainActivity.supportActionBar?.setDisplayHomeAsUpEnabled(false)

        activity!!.window.apply {
            (activity!! as AppCompatActivity).supportActionBar?.show()
            clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
            addFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN)
        }

        recycler.adapter = adapter

        checkViewsVisibility()

        btn.setOnClickListener {
            findNavController().navigate(R.id.action_cart_to_bottom_menu)
        }
        placeOrder.setOnClickListener {
            findNavController().navigate(R.id.action_cart_to_placeOrderFragment)
        }



    }

    private fun checkViewsVisibility() {
        if(adapter.itemCount > 0) {
            recycler.visibility = View.VISIBLE
            placeOrder.visibility = View.VISIBLE
            title.visibility = View.GONE
            text2.visibility = View.GONE
            btn.visibility = View.GONE
        } else {
            title.visibility = View.VISIBLE
            text2.visibility = View.VISIBLE
            btn.visibility = View.VISIBLE
            recycler.visibility = View.GONE
            placeOrder.visibility = View.GONE
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.cart_fragment_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.cart_menu_delete) {
            AlertDialog.Builder(activity!!, R.style.AlertDialog)
                .setTitle(getString(R.string.clear_cart))
                .setMessage(R.string.ask_all_delete)
                .setPositiveButton(getString(R.string.delete)) { dialog: DialogInterface, _: Int ->
                    clearAll()
                    dialog.dismiss()
                }
                .setNegativeButton(getString(R.string.cancel)) { dialog: DialogInterface, _: Int ->
                    dialog.dismiss()
                }
                .show()
        }
        return true
    }

    private fun clearAll() {
        activity!!.deleteAllCartItems()
        adapter.updateItems()
        adapter.notifyDataSetChanged()
        checkViewsVisibility()
        mainActivity.updateBadge()
    }



}
