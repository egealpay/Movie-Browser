package com.alpaye.moviebrowser.ui.favoritemovie

import android.content.Context
import android.content.Intent
import com.alpaye.moviebrowser.core.BaseActivity

class FavoriteMovieActivity : BaseActivity() {

    companion object {
        fun newIntent(context: Context) = Intent(context, FavoriteMovieActivity::class.java)
    }

    override fun getContainedFragment() = FavoriteMovieFragment.newInstance()

}