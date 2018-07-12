package com.alpaye.moviebrowser.ui.util

import android.content.Context
import android.content.Intent
import android.net.Uri

object Util{

    fun openImdbApp(context: Context, imdbId: String?) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse("imdb://title/$imdbId")
        if (intent.resolveActivity(context.packageManager) != null) {
            context.startActivity(intent)
            return
        }
        context.startActivity(Intent(Intent.ACTION_VIEW).also {
            it.data = Uri.parse("https://www.imdb.com/title/$imdbId")
        })
    }

    fun shareMovie(context: Context, movieTitle: String?) {
        val sharingIntent = Intent(android.content.Intent.ACTION_SEND)
        sharingIntent.type = "text/plain"
        val shareBody = "Hey bro, lets watch $movieTitle later on! "
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "$movieTitle")
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody)
        context.startActivity(Intent.createChooser(sharingIntent, "Share via"))
    }

}