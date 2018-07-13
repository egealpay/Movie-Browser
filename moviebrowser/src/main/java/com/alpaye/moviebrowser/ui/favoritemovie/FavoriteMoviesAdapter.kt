package com.alpaye.moviebrowser.ui.favoritemovie

import android.view.ViewGroup
import com.alpaye.moviebrowser.R
import com.alpaye.moviebrowser.ui.dashboard.Movie
import com.alpaye.moviebrowser.ui.dashboard.MovieViewHolder
import com.monitise.mea.android.ui.views.MTSEndlessRecyclerAdapter

class FavoriteMoviesAdapter(
        private val favoriteMovieList: ArrayList<Movie> = ArrayList()
) : MTSEndlessRecyclerAdapter<MovieViewHolder>(R.layout.layout_list_loading) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        super.onCreateViewHolder(parent, viewType)
        return MovieViewHolder(parent, R.layout.list_item_movie)
    }

    override fun getEndlessItemViewType(position: Int) = 0

    override fun getEndlessItemCount() = favoriteMovieList.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) = holder.bindMovie(favoriteMovieList[position])

    fun getFavoriteMovies() = favoriteMovieList

}