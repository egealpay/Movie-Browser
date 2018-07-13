package com.alpaye.moviebrowser.ui.dashboard

import android.annotation.SuppressLint
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@SuppressLint("ParcelCreator")
data class Movie(
        @SerializedName("id") val id: Int,
        @SerializedName("poster_path") val poster: String,
        @SerializedName("title") val title: String,
        @SerializedName("overview") val overview: String
) : Parcelable