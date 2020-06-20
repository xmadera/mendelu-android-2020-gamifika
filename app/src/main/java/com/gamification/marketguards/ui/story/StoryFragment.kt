package com.gamification.marketguards.ui.story

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.gamification.marketguards.R
import com.gamification.marketguards.ui.main.MainActivity

class StoryFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View? = inflater.inflate(R.layout.fragment_story, container, false)
        (activity as MainActivity).supportActionBar?.title = getString(R.string.story)

        return view
    }
}