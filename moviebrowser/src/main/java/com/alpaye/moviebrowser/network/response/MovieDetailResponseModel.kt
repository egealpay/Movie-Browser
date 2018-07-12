package com.alpaye.moviebrowser.network.response

import com.alpaye.moviebrowser.network.BaseResponseModel
import com.alpaye.moviebrowser.ui.moviedetails.Genre
import com.google.gson.annotations.SerializedName

data class MovieDetailResponseModel(
        @SerializedName("budget") val budget: Int? = null,
        @SerializedName("genres") val genresList: ArrayList<Genre>? = null,
        @SerializedName("poster_path") val poster: String? = null,
        @SerializedName("title") val title: String? = null,
        @SerializedName("release_date") val releaseDate: String? = null,
        @SerializedName("runtime") val runTime: Int? = null,
        @SerializedName("overview") val overview: String? = null,
        @SerializedName("imdb_id") val imdbId: String? = null
) : BaseResponseModel()