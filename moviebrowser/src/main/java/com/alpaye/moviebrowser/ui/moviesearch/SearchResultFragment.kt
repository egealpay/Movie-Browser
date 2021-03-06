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

private const val KEY_POSITION_INDEX = "positionIndex"
private const val KEY_PAGE_INDEX = "pageIndex"
private const val KEY_MOVIES = "movies"

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
    private var positionIndex = 0

    private lateinit var adapterMovies: MoviesRecyclerAdapter

    override fun getResourceLayoutId() = R.layout.fragment_search_result

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (null == savedInstanceState) {
            adapterMovies = MoviesRecyclerAdapter(listener = this)
            addRequest(MovieSearchRequest(arguments?.getString(SearchResultFragment.KEY_SEARCH_MOVIE_TITLE)!!, pageIndex))
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
            scrollToPosition(positionIndex)
        }
        addRequest(MovieSearchRequest(arguments?.getString(SearchResultFragment.KEY_SEARCH_MOVIE_TITLE)!!, pageIndex))

    }

    override fun onSaveInstanceState(outState: Bundle) {
        val layoutManager = endlessRecyclerViewSearchResult.layoutManager as LinearLayoutManager
        outState.putInt(KEY_POSITION_INDEX, layoutManager.findFirstVisibleItemPosition())
        outState.putParcelableArrayList(KEY_MOVIES, adapterMovies.getMovies())
        outState.putInt(KEY_PAGE_INDEX, pageIndex)
        super.onSaveInstanceState(outState)
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