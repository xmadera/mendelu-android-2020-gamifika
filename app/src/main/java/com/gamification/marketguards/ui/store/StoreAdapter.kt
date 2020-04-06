package com.gamification.marketguards.ui.store

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class StoreAdapter(activity: AppCompatActivity, private val itemsCount: Int) :
    FragmentStateAdapter(activity) {

  override fun getItemCount(): Int {
    return itemsCount
  }

  override fun createFragment(position: Int): Fragment {
    return StoreFragment.getInstance(position)
  }
}
