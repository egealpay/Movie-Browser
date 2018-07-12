package com.alpaye.moviebrowser.ui.moviesearch

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import butterknife.BindView
import com.alpaye.moviebrowser.R
import com.alpaye.moviebrowser.core.BaseFragment
import com.alpaye.moviebrowser.network.request.MovieSearchRequest
import com.alpaye.moviebrowser.network.response.MovieSearchResponse
import com.alpaye.moviebrowser.ui.dashboard.MoviesRecyclerAdapter
import com.alpaye.moviebrowser.ui.moviedetails.MovieDetailsActivity
import com.monitise.mea.android.network.bus.OnResponse
import com.monitise.mea.android.ui.adapters.MTSViewHolder
import com.monitise.mea.android.ui.views.MTSEndlessRecyclerView

class SearchResultFragment : BaseFragment(),
        MTSEndlessRecyclerView.OnEndReachedListener,
        MTSViewHolder.OnItemClickListener {

    companion object {
        private const val KEY_SEARCH_MOVIE_TITLE = "keySearchMovieTitle"
        fun newInstance(movieTitle: String): SearchResultFragment {
            val frag = SearchResultFragment()
            val bundle = Bundle().also {
                it.putString(KEY_SEARCH_MOVIE_TITLE, movieTitle)
            }
            frag.arguments = bundle
            return frag
        }
    }

    @BindView(R.id.fragment_search_result_toolbar)
    lateinit var toolbarSearchFragment: Toolbar

    @BindView(R.id.fragment_search_result_recyclerview)
    lateinit var endlessRecyclerViewSearchResult: MTSEndlessRecyclerView

    private var pageIndex = 1

    protected val adapterMovies: MoviesRecyclerAdapter by lazy { MoviesRecyclerAdapter(listener = this) }

    override fun getResourceLayoutId() = R.layout.fragment_search_result

    override fun initUserInterface(inflater: LayoutInflater, rootView: View) {
        super.initUserInterface(inflater, rootView)
        toolbarSearchFragment.title = arguments?.getString(
                SearchResultFragment.KEY_SEARCH_MOVIE_TITLE,
                resources.getString(R.string.app_name)
        )
        (activity as AppCompatActivity).setSupportActionBar(toolbarSearchFragment)
        with(endlessRecyclerViewSearchResult) {
            layoutManager = LinearLayoutManager(context)
            adapter = adapterMovies
            setLoading(false)
            setOnEndReachedListener(this@SearchResultFragment)
        }
        addRequest(MovieSearchRequest(arguments?.getString(SearchResultFragment.KEY_SEARCH_MOVIE_TITLE)!!, pageIndex))

    }

    @OnResponse
    fun onResponseNowPlaying(response: MovieSearchResponse) {
        pageIndex++
        endlessRecyclerViewSearchResult.setLoading(false)
        adapterMovies.updateList(response.movies)
    }

    override fun onEndReached() {
        endlessRecyclerViewSearchResult.setLoading(true)
        addRequest(MovieSearchRequest(arguments?.getString(SearchResultFragment.KEY_SEARCH_MOVIE_TITLE)!!, pageIndex))
    }

    override fun onItemClick(itemView: View, position: Int) {
        startActivity(MovieDetailsActivity.newIntent(context!!,
                adapterMovies.getMovieItemId(position),
                adapterMovies.getMovieItemTitle(position))
        )
    }

}