package com.alpaye.moviebrowser.network

import android.media.MediaFormat.KEY_LANGUAGE
import com.alpaye.moviebrowser.BuildConfig
import com.alpaye.moviebrowser.core.MBApp
import com.android.volley.AuthFailureError
import com.monitise.mea.android.network.bus.NetworkBus
import com.monitise.mea.android.network.bus.NetworkSubscriber
import com.monitise.mea.android.network.core.MTSError
import com.monitise.mea.android.network.core.MTSNetworkStack
import com.monitise.mea.android.network.requests.MTSBaseRequest
import com.monitise.mea.android.utils.MTSLanguageUtil
import java.util.*

private const val API_KEY_KEY = "api_key"
private const val PAGE_QUERY_KEY = "page"
private const val SEARCH_QUERY_KEY = "query"
private const val LANGUAGE_QUERY_KEY = "language"

abstract class BaseRequest<T : BaseRequestModel, V : BaseResponseModel> : MTSBaseRequest<V>() {

    init {
        addQueryParameter(API_KEY_KEY, BuildConfig.TMDB_API_KEY)
        addQueryParameter(
                LANGUAGE_QUERY_KEY,
                MTSLanguageUtil.Preferences.getLocale(MBApp.instance)?.language
        )
    }

    override fun deliverResponse(response: V) {
        if (mResponseListener is NetworkSubscriber) {
            NetworkBus.getInstance().onResponse(response, (mResponseListener as NetworkSubscriber).callerId)
        }
    }

    override fun deliverError(mtsError: MTSError) {
        NetworkBus.getInstance().onErrorResponse(mtsError, (mResponseListener as NetworkSubscriber).callerId)
    }

    override fun getActionUrl(): String {
        return MTSNetworkStack.getInstance().getAction(getActionUrlKey())
    }

    abstract fun getActionUrlKey(): String

    protected fun getPageQueryKey(): String {
        return PAGE_QUERY_KEY
    }

    protected fun getSearchQueryKey(): String = SEARCH_QUERY_KEY

    @Throws(AuthFailureError::class)
    override fun getHeaders(): Map<String, String> {

        val headers = super.getHeaders()
        headers[KEY_LANGUAGE] = Locale.getDefault().getLanguage()
        return headers
    }

    override fun getMethod(): Int {
        return Method.GET
    }

}