package com.alpaye.moviebrowser.ui.dashboard

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import butterknife.BindView
import com.alpaye.moviebrowser.R
import com.alpaye.moviebrowser.core.BaseFragment
import com.monitise.mea.android.ui.views.MTSEndlessRecyclerView

abstract class DashboardListFragment : BaseFragment(), MTSEndlessRecyclerView.OnEndReachedListener {

    @BindView(R.id.fragment_dashboard_list_recyclerview)
    lateinit var endlessRecyclerViewDashboard: MTSEndlessRecyclerView

    override fun getResourceLayoutId() = R.layout.fragment_dashboard_list

    private var pageIndex = 1

    protected val adapterMovies: MoviesRecyclerAdapter = MoviesRecyclerAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getMovies(pageIndex)
    }

    override fun initUserInterface(inflater: LayoutInflater, rootView: View) {
        super.initUserInterface(inflater, rootView)
        with(endlessRecyclerViewDashboard) {
            layoutManager = LinearLayoutManager(context)
            adapter = adapterMovies
            setLoading(false)
            setOnEndReachedListener(this@DashboardListFragment)
        }
    }

    override fun onEndReached() {
        endlessRecyclerViewDashboard.setLoading(true)
        getMovies(++pageIndex)
    }

    abstract fun getMovies(page: Int)

}
