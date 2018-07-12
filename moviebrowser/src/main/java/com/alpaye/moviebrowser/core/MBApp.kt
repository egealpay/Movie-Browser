package com.alpaye.moviebrowser.core

import android.content.res.Configuration
import com.facebook.drawee.backends.pipeline.Fresco
import com.google.gson.Gson
import com.monitise.mea.android.core.MTSApp
import com.monitise.mea.android.network.converter.MTSGsonConverter
import com.monitise.mea.android.network.core.MTSNetworkStack
import com.monitise.mea.android.utils.MTSLanguageUtil
import java.util.*


private const val URL_FILENAME = "url.properties"
private const val ACTIONS_FILENAME = "actions.properties"
private const val BASE_URL_KEY = "baseUrl"

class MBApp : MTSApp() {

    companion object {
        lateinit var instance: MBApp
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        MTSNetworkStack.init(MTSNetworkStack.Builder(this)
                .readUrlsFrom(URL_FILENAME)
                .readActionsFrom(ACTIONS_FILENAME)
                .setUrlKey(BASE_URL_KEY)
                .setConverter(MTSGsonConverter(Gson())))
        Fresco.initialize(this)
        initLocale()
    }

    override fun onConfigurationChanged(newConfig: Configuration?) {
        MTSLanguageUtil.handleOnConfigurationChanged(this, newConfig)
        super.onConfigurationChanged(newConfig)
    }

    private fun initLocale() {
        MTSLanguageUtil.setLocale(
                this,
                MTSLanguageUtil.Preferences.getLocale(this) ?: Locale.getDefault()
        )
    }

}