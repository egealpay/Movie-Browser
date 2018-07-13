package com.alpaye.moviebrowser.ui.dashboard

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import butterknife.BindView
import com.alpaye.moviebrowser.R
import com.alpaye.moviebrowser.core.BaseFragment
import com.alpaye.moviebrowser.ui.moviedetails.MovieDetailsActivity
import com.monitise.mea.android.ui.adapters.MTSViewHolder
import com.monitise.mea.android.ui.views.MTSEndlessRecyclerView

const private val KEY_POSITION_INDEX = "positionIndex"
const private val KEY_PAGE_INDEX = "pageIndex"
const private val KEY_MOVIES = "movies"

abstract class DashboardListFragment : BaseFragment(),
        MTSEndlessRecyclerView.OnEndReachedListener,
        MTSViewHolder.OnItemClickListener {

    @BindView(R.id.fragment_dashboard_list_recyclerview)
    lateinit var endlessRecyclerViewDashboard: MTSEndlessRecyclerView

    override fun getResourceLayoutId() = R.layout.fragment_dashboard_list

    private var pageIndex = 1
    private var positionIndex = 0

    private lateinit var adapterMovies: MoviesRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (null == savedInstanceState) {
            adapterMovies = MoviesRecyclerAdapter(listener = this)
            getMovies(pageIndex)
        } else {
            positionIndex = savedInstanceState.getInt(KEY_POSITION_INDEX)
            pageIndex = savedInstanceState.getInt(KEY_PAGE_INDEX)
            adapterMovies = MoviesRecyclerAdapter(
                    savedInstanceState.getParcelableArrayList(KEY_MOVIES),
                    this
            )
        }
    }

    override fun initUserInterface(inflater: LayoutInflater, rootView: View) {
        super.initUserInterface(inflater, rootView)
        with(endlessRecyclerViewDashboard) {
            layoutManager = LinearLayoutManager(context)
            adapter = adapterMovies
            setLoading(false)
            setOnEndReachedListener(this@DashboardListFragment)
            scrollToPosition(positionIndex)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        val layoutManager = endlessRecyclerViewDashboard.layoutManager as LinearLayoutManager
        outState.putInt(KEY_POSITION_INDEX, layoutManager.findFirstVisibleItemPosition())
        outState.putParcelableArrayList(KEY_MOVIES, adapterMovies.getMovies())
        outState.putInt(KEY_PAGE_INDEX, pageIndex)
        super.onSaveInstanceState(outState)
    }

    protected fun updateList(movies: ArrayList<Movie>?){
        pageIndex++
        endlessRecyclerViewDashboard.setLoading(false)
        adapterMovies.updateList(movies)
    }

    override fun onEndReached() {
        endlessRecyclerViewDashboard.setLoading(true)
        getMovies(pageIndex)
    }

    override fun onItemClick(itemView: View, position: Int) {
        startActivity(MovieDetailsActivity.newIntent(context!!,
                adapterMovies.getMovieItemId(position),
                adapterMovies.getMovieItemTitle(position))
        )
    }

    abstract fun getMovies(page: Int)

}
