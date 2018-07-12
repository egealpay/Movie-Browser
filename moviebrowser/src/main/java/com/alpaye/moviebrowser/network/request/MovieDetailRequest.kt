package com.alpaye.moviebrowser.network.request

import com.alpaye.moviebrowser.network.BaseRequest
import com.alpaye.moviebrowser.network.BaseRequestModel
import com.alpaye.moviebrowser.network.response.MovieDetailResponseModel

class MovieDetailRequest(movieId: Int) : BaseRequest<BaseRequestModel, MovieDetailResponseModel>() {

    init {
        addPathParameter(Integer.toString(movieId))
    }

    override fun getActionUrlKey() = "details"

    override fun getResponseModelClass() = MovieDetailResponseModel::class.java

}