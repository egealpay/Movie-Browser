package com.alpaye.moviebrowser.ui.dashboard.items

import com.alpaye.moviebrowser.network.request.UpComingRequest
import com.alpaye.moviebrowser.network.response.UpComingResponse
import com.alpaye.moviebrowser.ui.dashboard.DashboardListFragment
import com.hannesdorfmann.fragmentargs.annotation.FragmentWithArgs
import com.monitise.mea.android.network.bus.OnResponse

@FragmentWithArgs
class UpComingFragment : DashboardListFragment() {

    override fun getMovies(page: Int) {
        addRequest(UpComingRequest(page))
    }

    @OnResponse
    fun onResponseNowPlaying(response: UpComingResponse) {
        endlessRecyclerViewDashboard.setLoading(false)
        adapterMovies.updateList(response.movies)
    }

}