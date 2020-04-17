package com.gamification.marketguards.ui.store

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.gamification.marketguards.constants.IntentConstants
import com.gamification.marketguards.R

class StoreFragment : Fragment() {

    fun newInstance(sub_tab_name: String): StoreFragment {
        val newFragment = StoreFragment()
        val dataBundle = Bundle()
        dataBundle.putString(IntentConstants.SUB_TAB_NAME, sub_tab_name)
        newFragment.arguments = dataBundle
        return newFragment
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View? = inflater.inflate(R.layout.fragment_store, container, false)

        val text: TextView = view!!.findViewById(R.id.text)
        text.text = arguments!!.getString(IntentConstants.SUB_TAB_NAME).toString()

        return view
    }

}
