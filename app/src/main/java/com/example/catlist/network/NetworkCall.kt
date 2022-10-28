package com.example.catlist.network

import com.example.catlist.domain.model.CatDetail
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkCall {

    @GET("search?format=json&size=med&limit=10")
    suspend fun getDetail(@Query("page") pageNo: String): Response<List<CatDetail>>
}