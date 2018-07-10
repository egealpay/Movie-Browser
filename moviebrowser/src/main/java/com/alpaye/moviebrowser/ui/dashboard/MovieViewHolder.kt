package com.alpaye.moviebrowser.ui.dashboard

import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import butterknife.BindView
import com.alpaye.moviebrowser.R
import com.monitise.mea.android.ui.adapters.MTSViewHolder

class MovieViewHolder(
        parent: ViewGroup,
        layoutId: Int
) : MTSViewHolder(parent, layoutId) {

    @BindView(R.id.list_item_imageview_poster)
    lateinit var imageViewMoviePoster: ImageView

    @BindView(R.id.list_item_textview_title)
    lateinit var textViewMovieTitle: TextView

    @BindView(R.id.list_item_textview_releasedate)
    lateinit var textViewMovieReleaseDate: TextView

    @BindView(R.id.list_item_textview_vote)
    lateinit var textViewMovieVote: TextView

    fun bindMovie(movie: Movie){
        //moviePoster.setImageURI()
        textViewMovieTitle.text = movie.title
        textViewMovieReleaseDate.text = movie.release_date
        textViewMovieVote.text = movie.vote_average.toString()
    }

}