package com.alpaye.moviebrowser.ui.favoritemovie

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import butterknife.BindView
import com.alpaye.moviebrowser.R
import com.alpaye.moviebrowser.core.BaseFragment
import com.alpaye.moviebrowser.database.FavoriteMovieDatabase
import com.monitise.mea.android.network.bus.OnResponse
import com.monitise.mea.android.ui.views.MTSEndlessRecyclerView

private const val KEY_POSITION_INDEX = "positionIndex"
private const val KEY_MOVIES = "movies"

class FavoriteMovieFragment : BaseFragment() {

    companion object {
        fun newInstance() = FavoriteMovieFragment()
    }

    override fun getResourceLayoutId() = R.layout.fragment_favorite_movies

    @BindView(R.id.fragment_favorite_movies_toolbar)
    lateinit var toolbarFavoriteMoviesFragment: Toolbar

    @BindView(R.id.fragment_favorite_movies_recyclerview)
    lateinit var endlessRecyclerViewFavoriteMovies: MTSEndlessRecyclerView

    private var positionIndex = 0

    private lateinit var adapterFavoriteMovies: FavoriteMoviesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (null == savedInstanceState) {
            val db = FavoriteMovieDatabase.getFavoriteMovieDatabase(context!!)
            val favoriteMoviesList = ArrayList(db.favoriteMovieDao().getFavoriteMovies())
            adapterFavoriteMovies = FavoriteMoviesAdapter(favoriteMoviesList)
        } else {
            positionIndex = savedInstanceState.getInt(KEY_POSITION_INDEX)
            adapterFavoriteMovies = FavoriteMoviesAdapter(
                    savedInstanceState.getParcelableArrayList(KEY_MOVIES)
            )
        }
    }

    override fun initUserInterface(inflater: LayoutInflater, rootView: View) {
        super.initUserInterface(inflater, rootView)
        toolbarFavoriteMoviesFragment.title = getString(R.string.favorites)
        (activity as AppCompatActivity).setSupportActionBar(toolbarFavoriteMoviesFragment)
        with(endlessRecyclerViewFavoriteMovies) {
            layoutManager = LinearLayoutManager(context)
            adapter = adapterFavoriteMovies
            setLoading(false)
            scrollToPosition(positionIndex)
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        val layoutManager = endlessRecyclerViewFavoriteMovies.layoutManager as LinearLayoutManager
        outState.putInt(KEY_POSITION_INDEX, layoutManager.findFirstVisibleItemPosition())
        outState.putParcelableArrayList(KEY_MOVIES, adapterFavoriteMovies.getFavoriteMovies())
        super.onSaveInstanceState(outState)
    }

    @OnResponse
    fun onResponse(response: OnResponse) {
        Log.d("OnResponse: ", response.toString())
    }

}