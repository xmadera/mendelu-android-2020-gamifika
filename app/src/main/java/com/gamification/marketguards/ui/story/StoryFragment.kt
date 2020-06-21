package com.gamification.marketguards.ui.story

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gamification.marketguards.R
import com.gamification.marketguards.data.base.BaseFragment
import com.gamification.marketguards.ui.main.MainActivity

class StoryFragment : BaseFragment() {

    override val layout: Int = R.layout.fragment_story

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View? = inflater.inflate(layout, container, false)
        (activity as MainActivity).supportActionBar?.title = getString(R.string.story)

        return view
    }
}