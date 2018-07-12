package com.alpaye.moviebrowser.ui.moviesearch

import android.content.Context
import android.content.Intent
import com.alpaye.moviebrowser.core.BaseActivity

class SearchResultActivity : BaseActivity() {

    companion object {
        private const val KEY_SEARCH_MOVIE_TITLE = "keySearchMovieTitle"
        fun newIntent(context: Context, movieTitle: String): Intent {
            return Intent(context, SearchResultActivity::class.java).putExtra(KEY_SEARCH_MOVIE_TITLE, movieTitle)
        }
    }

    private val movieTitle by lazy { intent.getStringExtra(KEY_SEARCH_MOVIE_TITLE) }

    override fun getContainedFragment() = SearchResultFragment.newInstance(movieTitle)

}