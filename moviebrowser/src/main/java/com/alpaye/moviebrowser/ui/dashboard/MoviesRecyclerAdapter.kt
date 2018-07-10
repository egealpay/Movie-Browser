package com.alpaye.moviebrowser.ui.dashboard

import android.view.ViewGroup
import com.alpaye.moviebrowser.R
import com.monitise.mea.android.ui.views.MTSEndlessRecyclerAdapter

class MoviesRecyclerAdapter(
        private val movieList: ArrayList<Movie> = ArrayList()
) : MTSEndlessRecyclerAdapter<MovieViewHolder>(R.layout.layout_list_loading) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        super.onCreateViewHolder(parent, viewType)
        return MovieViewHolder(parent, R.layout.list_item_movie)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bindMovie(movieList[position])
    }

    override fun getEndlessItemViewType(position: Int) = 0

    override fun getEndlessItemCount() = movieList.size

    fun updateList(movies: ArrayList<Movie>?) {
        movies?.let {
            movieList.addAll(it)
            notifyDataSetChanged()
        }
    }

}