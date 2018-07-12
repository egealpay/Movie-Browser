package com.alpaye.moviebrowser.network.request

import com.alpaye.moviebrowser.network.BaseRequest
import com.alpaye.moviebrowser.network.BaseRequestModel
import com.alpaye.moviebrowser.network.response.MovieSearchResponse

class MovieSearchRequest(searchQuery: String, page: Int) : BaseRequest<BaseRequestModel, MovieSearchResponse>() {

    init {
        addQueryParameter(getPageQueryKey(), Integer.toString(page))
        addQueryParameter(getSearchQueryKey(), searchQuery)
    }

    override fun getActionUrlKey() = "search"

    override fun getResponseModelClass() = MovieSearchResponse::class.java
}