package com.alpaye.moviebrowser.ui.dashboard

import com.google.gson.annotations.SerializedName

data class Movie(
        @SerializedName("id") val id: Int,
        @SerializedName("poster_path") val poster: String,
        @SerializedName("title") val title: String,
        @SerializedName("overview") val overview: String
)