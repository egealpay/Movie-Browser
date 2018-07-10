package com.alpaye.moviebrowser.network.request

import com.alpaye.moviebrowser.network.BaseRequest
import com.alpaye.moviebrowser.network.BaseRequestModel
import com.alpaye.moviebrowser.network.response.PopularResponse

class PopularRequest(page: Int) : BaseRequest<BaseRequestModel, PopularResponse>() {

    init {
        addQueryParameter(getPageQueryKey(), Integer.toString(page))
    }

    override fun getActionUrlKey() = "popular"

    override fun getResponseModelClass() = PopularResponse::class.java

}