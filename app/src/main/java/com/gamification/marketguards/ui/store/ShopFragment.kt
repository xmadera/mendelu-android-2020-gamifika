package com.gamification.marketguards.ui.store

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.gamification.marketguards.R
import com.gamification.marketguards.data.base.BaseFragment
import com.gamification.marketguards.data.constants.IntentConstants

class ShopFragment : BaseFragment() {

    fun newInstance(sub_tab_name: String): ShopFragment {
        val newFragment = ShopFragment()
        val dataBundle = Bundle()
        dataBundle.putString(IntentConstants.SUB_TAB_NAME, sub_tab_name)
        newFragment.arguments = dataBundle
        return newFragment
    }

    override val layout: Int = R.layout.fragment_shop

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View? = inflater.inflate(layout, container, false)

        val text: TextView = view!!.findViewById(R.id.shopText)
        text.text = arguments!!.getString(IntentConstants.SUB_TAB_NAME).toString()

        return view
    }
}
