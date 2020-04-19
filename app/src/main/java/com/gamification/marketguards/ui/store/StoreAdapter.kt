package com.gamification.marketguards.ui.store

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class StoreAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
  private val fragmentsList: MutableList<Fragment> = mutableListOf()

  fun addFragment(fragment: Fragment) {
    fragmentsList.add(fragment)
  }

  fun deleteFragments() {
    fragmentsList.clear()
    notifyDataSetChanged()
  }

  override fun getItemCount(): Int {
    return fragmentsList.size
  }

  override fun createFragment(position: Int): Fragment {
    return fragmentsList.get(position)
  }

  override fun getItemId(position: Int): Long {
    return super.getItemId(position)
  }

}