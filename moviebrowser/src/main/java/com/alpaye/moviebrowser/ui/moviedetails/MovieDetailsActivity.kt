package com.alpaye.moviebrowser.ui.moviedetails

import android.content.Context
import android.content.Intent
import com.alpaye.moviebrowser.core.BaseActivity

class MovieDetailsActivity : BaseActivity() {

    companion object {
        private const val KEY_MOVIE_ID = "keyMovieId"
        private const val KEY_MOVIE_TITLE = "keyMovieTitle"
        fun newIntent(context: Context, movieId: Int, movieTitle: String): Intent {
            return Intent(context, MovieDetailsActivity::class.java).also {
                it.putExtra(KEY_MOVIE_ID, movieId)
                it.putExtra(KEY_MOVIE_TITLE, movieTitle)
            }
        }
    }

    private val movieId by lazy { intent.getIntExtra(KEY_MOVIE_ID, 0) }
    private val movieTitle by lazy { intent.getStringExtra(KEY_MOVIE_TITLE) }

    override fun getContainedFragment() = MovieDetailsFragment.newInstance(movieId, movieTitle)

}