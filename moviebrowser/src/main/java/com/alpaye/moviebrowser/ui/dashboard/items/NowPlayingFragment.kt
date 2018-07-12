package com.alpaye.moviebrowser.ui.dashboard.items

import com.alpaye.moviebrowser.network.request.NowPlayingRequest
import com.alpaye.moviebrowser.network.response.NowPlayingResponse
import com.alpaye.moviebrowser.ui.dashboard.DashboardListFragment
import com.hannesdorfmann.fragmentargs.annotation.FragmentWithArgs
import com.monitise.mea.android.network.bus.OnResponse

@FragmentWithArgs
class NowPlayingFragment : DashboardListFragment() {

    override fun getMovies(page: Int) = addRequest(NowPlayingRequest(page))

    @OnResponse
    fun onResponseNowPlaying(response: NowPlayingResponse) = updateList(response.movies)

}