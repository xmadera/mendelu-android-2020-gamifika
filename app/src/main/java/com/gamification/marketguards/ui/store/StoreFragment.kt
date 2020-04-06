package com.gamification.marketguards.ui.store

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.gamification.marketguards.R
import kotlinx.android.synthetic.main.fragment_store.*

class StoreFragment : Fragment() {

    companion object {
        const val ARG_POSITION = "position"

        fun getInstance(position: Int): Fragment {
            val storeFragment = StoreFragment()
            val bundle = Bundle()
            bundle.putInt(ARG_POSITION, position)
            storeFragment.arguments = bundle
            return storeFragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_store, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val position = requireArguments().getInt(ARG_POSITION)
        val Pages = requireContext().resources.getStringArray(R.array.StoreSubTabs)

        text.text = Pages[position]
    }

}
