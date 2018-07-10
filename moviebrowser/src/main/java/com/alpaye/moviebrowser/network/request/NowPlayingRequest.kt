package com.alpaye.moviebrowser.network.request

import com.alpaye.moviebrowser.network.BaseRequest
import com.alpaye.moviebrowser.network.BaseRequestModel
import com.alpaye.moviebrowser.network.response.NowPlayingResponse

class NowPlayingRequest(page: Int) : BaseRequest<BaseRequestModel, NowPlayingResponse>() {

    init {
        addQueryParameter(getPageQueryKey(), Integer.toString(page))
    }

    override fun getActionUrlKey() = "nowPlaying"

    override fun getResponseModelClass() = NowPlayingResponse::class.java

}