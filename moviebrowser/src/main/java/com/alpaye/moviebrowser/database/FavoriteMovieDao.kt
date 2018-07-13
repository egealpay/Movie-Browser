package com.alpaye.moviebrowser.database

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.alpaye.moviebrowser.ui.dashboard.Movie

@Dao
interface FavoriteMovieDao {

    @Query("SELECT * FROM favorite_movie")
    fun getFavoriteMovies(): List<Movie>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addFavoriteMovie(favoriteMovie: Movie)

}