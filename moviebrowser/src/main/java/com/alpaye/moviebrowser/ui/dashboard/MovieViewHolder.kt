package com.alpaye.moviebrowser.ui.dashboard

import android.net.Uri
import android.view.ViewGroup
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.alpaye.moviebrowser.R
import com.facebook.drawee.view.SimpleDraweeView
import com.monitise.mea.android.network.core.MTSNetworkStack
import com.monitise.mea.android.ui.adapters.MTSViewHolder

class MovieViewHolder(
        parent: ViewGroup,
        layoutId: Int
) : MTSViewHolder(parent, layoutId) {

    init {
        ButterKnife.bind(this, itemView)
    }

    @BindView(R.id.list_item_simpledraweeview_poster)
    lateinit var simpleDraweeViewMoviePoster: SimpleDraweeView

    @BindView(R.id.list_item_textview_title)
    lateinit var textViewMovieTitle: TextView

    @BindView(R.id.list_item_textview_overview)
    lateinit var textViewMovieOverview: TextView

    fun bindMovie(movie: Movie) {
        simpleDraweeViewMoviePoster.setImageURI(Uri.parse(MTSNetworkStack.getInstance().getUrl("posterBaseUrl") + movie.poster))
        textViewMovieTitle.text = movie.title
        textViewMovieOverview.text = movie.overview
    }

}