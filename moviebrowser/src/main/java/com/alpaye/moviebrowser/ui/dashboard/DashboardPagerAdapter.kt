package com.alpaye.moviebrowser.ui.dashboard

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.alpaye.moviebrowser.ui.dashboard.items.NowPlayingFragmentBuilder
import com.alpaye.moviebrowser.ui.dashboard.items.PopularFragmentBuilder
import com.alpaye.moviebrowser.ui.dashboard.items.TopRatedFragmentBuilder
import com.alpaye.moviebrowser.ui.dashboard.items.UpComingFragmentBuilder

private const val SIZE: Int = 4

class DashboardPagerAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> UpComingFragmentBuilder().build()
            1 -> NowPlayingFragmentBuilder().build()
            2 -> PopularFragmentBuilder().build()
            3 -> TopRatedFragmentBuilder().build()
            else -> throw IllegalArgumentException("Unexpected position")
        }
    }

    override fun getCount(): Int {
        return SIZE
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "Upcoming"
            1 -> "Now Playing"
            2 -> "Popular"
            3 -> "Top Rated"
            else -> throw IllegalArgumentException("Unexpected position")
        }
    }

}