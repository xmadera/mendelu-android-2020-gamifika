package com.gamification.marketguards.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toolbar
import androidx.fragment.app.Fragment
import com.gamification.marketguards.R
import com.gamification.marketguards.constants.IntentConstants
import com.gamification.marketguards.ui.main.MainActivity


class DashboardFragment: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View? = inflater.inflate(R.layout.fragment_dashboard, container, false)
        (activity as MainActivity).supportActionBar?.title = getString(R.string.dashboard)

        return view
    }
}