package com.alpaye.moviebrowser.network.response

import com.alpaye.moviebrowser.network.BaseResponseModel
import com.alpaye.moviebrowser.ui.dashboard.Movie
import com.google.gson.annotations.SerializedName

abstract class MovieResponseModel(
        @SerializedName("results") val movies : ArrayList<Movie>? = null
) : BaseResponseModel()