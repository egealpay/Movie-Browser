package com.alpaye.moviebrowser.network.request

import com.alpaye.moviebrowser.network.BaseRequest
import com.alpaye.moviebrowser.network.BaseRequestModel
import com.alpaye.moviebrowser.network.response.UpComingResponse

class UpComingRequest(page: Int) : BaseRequest<BaseRequestModel, UpComingResponse>() {

    init {
        addQueryParameter(getPageQueryKey(), Integer.toString(page))
    }

    override fun getActionUrlKey() = "upcoming"

    override fun getResponseModelClass() = UpComingResponse::class.java

}