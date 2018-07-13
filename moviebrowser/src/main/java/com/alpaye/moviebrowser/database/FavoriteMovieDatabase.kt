package com.alpaye.moviebrowser.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.alpaye.moviebrowser.ui.dashboard.Movie

@Database(entities = arrayOf(Movie::class), version = 1)
abstract class FavoriteMovieDatabase : RoomDatabase() {

    abstract fun favoriteMovieDao(): FavoriteMovieDao

    companion object {
        private var INSTANCE: FavoriteMovieDatabase? = null

        fun getFavoriteMovieDatabase(context: Context): FavoriteMovieDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.applicationContext, FavoriteMovieDatabase::class.java, "favorite_movie.db")
                        .allowMainThreadQueries()
                        .build()
            }
            return INSTANCE!!
        }
    }

}