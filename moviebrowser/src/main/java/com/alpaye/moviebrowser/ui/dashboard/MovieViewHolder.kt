package com.alpaye.moviebrowser.ui.dashboard

import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.alpaye.moviebrowser.R
import com.monitise.mea.android.ui.adapters.MTSViewHolder

class MovieViewHolder(
        parent: ViewGroup,
        layoutId: Int
) : MTSViewHolder(parent, layoutId) {

    init {
        ButterKnife.bind(this, itemView)
    }

    @BindView(R.id.list_item_imageview_poster)
    lateinit var imageViewMoviePoster: ImageView

    @BindView(R.id.list_item_textview_title)
    lateinit var textViewMovieTitle: TextView

    @BindView(R.id.list_item_textview_overview)
    lateinit var textViewMovieOverview: TextView

    fun bindMovie(movie: Movie){
        //moviePoster.setImageURI()
        textViewMovieTitle.text = movie.title
        textViewMovieOverview.text = movie.overview
    }

}