package com.alpaye.moviebrowser.ui.dashboard

import android.annotation.SuppressLint
import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@SuppressLint("ParcelCreator")
@Entity(tableName = "favorite_movie")
data class Movie(

        @PrimaryKey
        @SerializedName("id")
        val id: Int = -1,

        @ColumnInfo(name = "poster_path")
        @SerializedName("poster_path")
        val poster: String,

        @ColumnInfo(name = "title")
        @SerializedName("title")
        val title: String,

        @ColumnInfo(name = "overview")
        @SerializedName("overview")
        val overview: String

) : Parcelable