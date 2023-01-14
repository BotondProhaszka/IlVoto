package hu.proha.ilvoto.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import hu.proha.ilvoto.fragments.EventsFragment
import hu.proha.ilvoto.fragments.ProfileFragment

class ViewsPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    companion object{
        private const val NUM_PAGES = 2
    }

    override fun getItemCount() = NUM_PAGES

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> ProfileFragment()
            1 -> EventsFragment()
            else -> ProfileFragment()
        }
    }


}