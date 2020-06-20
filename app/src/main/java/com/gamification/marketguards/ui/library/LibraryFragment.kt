package com.gamification.marketguards.ui.library

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.gamification.marketguards.R
import com.gamification.marketguards.ui.main.MainActivity

class LibraryFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View? = inflater.inflate(R.layout.fragment_library, container, false)
        (activity as MainActivity).supportActionBar?.title = getString(R.string.library)

        return view
    }
}
