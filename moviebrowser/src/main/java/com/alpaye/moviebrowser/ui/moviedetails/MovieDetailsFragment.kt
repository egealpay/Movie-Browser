package com.alpaye.moviebrowser.ui.moviedetails

import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import butterknife.BindView
import butterknife.OnClick
import com.alpaye.moviebrowser.R
import com.alpaye.moviebrowser.core.BaseFragment
import com.alpaye.moviebrowser.network.request.MovieDetailRequest
import com.alpaye.moviebrowser.network.response.MovieDetailResponseModel
import com.alpaye.moviebrowser.ui.util.Util
import com.facebook.drawee.view.SimpleDraweeView
import com.monitise.mea.android.network.bus.OnResponse
import com.monitise.mea.android.network.core.MTSNetworkStack
import com.monitise.mea.android.ui.views.MTSKeyValueLayout


class MovieDetailsFragment : BaseFragment() {

    private var movieTitle: String? = null
    private var imdbId: String? = null

    companion object {
        private const val KEY_MOVIE_ID = "keyMovieId"
        private const val KEY_MOVIE_TITLE = "keyMovieTitle"
        fun newInstance(movieId: Int, movieTitle: String): MovieDetailsFragment {
            val frag = MovieDetailsFragment()
            val bundle = Bundle().also {
                it.putInt(KEY_MOVIE_ID, movieId)
                it.putString(KEY_MOVIE_TITLE, movieTitle)
            }
            frag.arguments = bundle
            return frag
        }
    }

    @BindView(R.id.activity_movie_details_toolbar)
    lateinit var toolbarMovieDetails: Toolbar

    @BindView(R.id.activity_movie_details_simpledraweeview_toolbarposter)
    lateinit var simpleDraweeToolbarMoviePoster: SimpleDraweeView

    @BindView(R.id.activity_movie_details_textview_genres)
    lateinit var textViewMovieGenres: TextView

    @BindView(R.id.activity_movie_details_simpledraweeview_poster)
    lateinit var simpleDraweeMoviePoster: SimpleDraweeView

    @BindView(R.id.activity_movie_details_keyvaluelayout_released)
    lateinit var keyValueLayoutReleaseDate: MTSKeyValueLayout

    @BindView(R.id.activity_movie_details_keyvaluelayout_budget)
    lateinit var keyValueLayoutBudget: MTSKeyValueLayout

    @BindView(R.id.activity_movie_details_keyvaluelayout_runtime)
    lateinit var keyValueLayoutRuntime: MTSKeyValueLayout

    @BindView(R.id.activity_movie_details_textview_overview)
    lateinit var textViewMovieOverview: TextView

    override fun getResourceLayoutId() = R.layout.fragment_movie_details

    override fun initUserInterface(inflater: LayoutInflater, rootView: View) {
        super.initUserInterface(inflater, rootView)
        toolbarMovieDetails.title = arguments?.getString(KEY_MOVIE_TITLE, "Movie Browser")
        (activity as AppCompatActivity).setSupportActionBar(toolbarMovieDetails)
        addRequest(MovieDetailRequest(arguments?.getInt(KEY_MOVIE_ID, 0)!!))
    }

    @OnResponse
    fun onResponseMovieDetails(response: MovieDetailResponseModel) {
        simpleDraweeToolbarMoviePoster.setImageURI(Uri.parse(MTSNetworkStack.getInstance().getUrl("posterBaseUrl") + response.poster))

        var genres = ""
        for (genre in response.genresList!!) {
            genres += genre.name + "\t"
        }

        textViewMovieGenres.text = genres
        simpleDraweeMoviePoster.setImageURI(Uri.parse(MTSNetworkStack.getInstance().getUrl("posterBaseUrl") + response.poster))
        keyValueLayoutReleaseDate.setValue(response.releaseDate)
        keyValueLayoutBudget.setValue(response.budget.toString())
        keyValueLayoutRuntime.setValue(response.runTime.toString())
        textViewMovieOverview.text = response.overview

        movieTitle = response.title
        imdbId = response.imdbId
    }

    @OnClick(R.id.activity_movie_details_imagebutton_imdb)
    fun openImdbApp() {
        Util.openImdbApp(context!!, imdbId)
    }

    @OnClick(R.id.activity_movie_details_imagebutton_share)
    fun shareMovie() {
        Util.shareMovie(context!!, movieTitle)
    }

    @OnClick(R.id.activity_movie_details_imagebutton_favorite)
    fun addMovieToFavorites() {
        //ToDo: Implement Room
    }

}