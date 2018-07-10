package com.alpaye.moviebrowser.network.request

import com.alpaye.moviebrowser.network.BaseRequest
import com.alpaye.moviebrowser.network.BaseRequestModel
import com.alpaye.moviebrowser.network.response.TopRatedResponse
import com.alpaye.moviebrowser.network.response.UpComingResponse

class TopRatedRequest(page: Int) : BaseRequest<BaseRequestModel, TopRatedResponse>() {

    init {
        addQueryParameter(getPageQueryKey(), Integer.toString(page))
    }

    override fun getActionUrlKey() = "topRated"

    override fun getResponseModelClass() = TopRatedResponse::class.java
}