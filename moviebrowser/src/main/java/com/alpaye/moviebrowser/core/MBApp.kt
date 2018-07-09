package com.alpaye.moviebrowser.core

import com.google.gson.Gson
import com.monitise.mea.android.core.MTSApp
import com.monitise.mea.android.network.converter.MTSGsonConverter
import com.monitise.mea.android.network.core.MTSNetworkStack


private const val URL_FILENAME = "url.properties"
private const val ACTIONS_FILENAME = "actions.properties"
private const val BASE_URL_KEY = "baseUrl"

class MBApp : MTSApp() {

    override fun onCreate() {
        super.onCreate()
        MTSNetworkStack.init(MTSNetworkStack.Builder(this)
                .readUrlsFrom(URL_FILENAME)
                .readActionsFrom(ACTIONS_FILENAME)
                .setUrlKey(BASE_URL_KEY)
                .setConverter(MTSGsonConverter(Gson())))
    }

}