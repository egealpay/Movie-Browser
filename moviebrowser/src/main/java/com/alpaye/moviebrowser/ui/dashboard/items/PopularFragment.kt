package com.alpaye.moviebrowser.ui.dashboard.items

import com.alpaye.moviebrowser.network.request.PopularRequest
import com.alpaye.moviebrowser.network.response.PopularResponse
import com.alpaye.moviebrowser.ui.dashboard.DashboardListFragment
import com.hannesdorfmann.fragmentargs.annotation.FragmentWithArgs
import com.monitise.mea.android.network.bus.OnResponse

@FragmentWithArgs
class PopularFragment : DashboardListFragment() {

    override fun getMovies(page: Int) {
        addRequest(PopularRequest(page))
    }

    @OnResponse
    fun onResponseNowPlaying(response: PopularResponse) {
        endlessRecyclerViewDashboard.setLoading(false)
        adapterMovies.updateList(response.movies)
    }

}