package com.gamification.marketguards.ui.store

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TableLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.gamification.marketguards.constants.IntentConstants
import com.gamification.marketguards.R
import com.gamification.marketguards.ui.main.MainActivity
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_store.*
import kotlinx.android.synthetic.main.fragment_store.*

class StoreFragment : Fragment() {


    private lateinit var adapter: StoreAdapter
    private lateinit var storePages: Array<String>


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View? = inflater.inflate(R.layout.fragment_store, container, false)
        (activity as MainActivity).supportActionBar?.title = getString(R.string.store)

        storePages = resources.getStringArray(R.array.StoreSubTabs)

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setupViewPager()
        setupTabLayout()
    }

    private fun setupViewPager() {
        adapter = StoreAdapter(activity!!)
        val fragment1: ShopFragment = ShopFragment().newInstance(storePages[0])
        adapter.addFragment(fragment1)
        val fragment2: ProductsFragment = ProductsFragment().newInstance(storePages[1])
        adapter.addFragment(fragment2)
        storeViewPager!!.adapter = adapter
    }

    private fun setupTabLayout() {
        TabLayoutMediator(storeTabLayout, storeViewPager) { tab, position ->
            tab.text = storePages[position].substringBefore(' ')
        }.attach()
    }

}
