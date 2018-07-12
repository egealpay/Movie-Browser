package com.alpaye.moviebrowser.ui.dashboard

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.alpaye.moviebrowser.R
import com.alpaye.moviebrowser.ui.dashboard.items.NowPlayingFragmentBuilder
import com.alpaye.moviebrowser.ui.dashboard.items.PopularFragmentBuilder
import com.alpaye.moviebrowser.ui.dashboard.items.TopRatedFragmentBuilder
import com.alpaye.moviebrowser.ui.dashboard.items.UpComingFragmentBuilder

private const val SIZE: Int = 4

class DashboardPagerAdapter(
        private val context: Context,
        fragmentManager: FragmentManager
) : FragmentPagerAdapter(fragmentManager) {

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
            0 -> context.resources.getString(R.string.fragment_upcoming)
            1 -> context.resources.getString(R.string.fragment_nowplaying)
            2 -> context.resources.getString(R.string.fragment_popular)
            3 -> context.resources.getString(R.string.fragment_toprating)
            else -> throw IllegalArgumentException("Unexpected position")
        }
    }

}