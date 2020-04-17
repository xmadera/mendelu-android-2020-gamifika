package com.gamification.marketguards.ui.store

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class StoreAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
  private val fragmentsList: MutableList<StoreFragment> = mutableListOf()

  fun addFragment(fragment: StoreFragment) {
    fragmentsList.add(fragment)
  }

  override fun getItemCount(): Int {
    return fragmentsList.size
  }

  override fun createFragment(position: Int): Fragment {
    return fragmentsList.get(position)
  }

}
