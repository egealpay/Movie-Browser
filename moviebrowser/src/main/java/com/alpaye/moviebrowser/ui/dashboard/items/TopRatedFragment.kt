package com.alpaye.moviebrowser.ui.dashboard.items

import com.alpaye.moviebrowser.network.request.TopRatedRequest
import com.alpaye.moviebrowser.network.response.TopRatedResponse
import com.alpaye.moviebrowser.ui.dashboard.DashboardListFragment
import com.hannesdorfmann.fragmentargs.annotation.FragmentWithArgs
import com.monitise.mea.android.network.bus.OnResponse

@FragmentWithArgs
class TopRatedFragment : DashboardListFragment() {

    override fun getMovies(page: Int) {
        addRequest(TopRatedRequest(page))
    }

    @OnResponse
    fun onResponseNowPlaying(response: TopRatedResponse) {
        endlessRecyclerViewDashboard.setLoading(false)
        adapterMovies.updateList(response.movies)
    }

}