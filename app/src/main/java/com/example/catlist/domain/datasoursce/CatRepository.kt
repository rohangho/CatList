package com.example.catlist.domain.datasoursce

import com.example.catlist.domain.model.CatDetail
import com.example.catlist.network.NetworkCall
import retrofit2.Retrofit
import javax.inject.Inject

class CatRepository @Inject constructor(private val retrofitInstance: Retrofit) {
    suspend fun getData(): ResponseState {
        val responseData = retrofitInstance.create(NetworkCall::class.java).getDetail("0");
        if (responseData.isSuccessful)
            responseData.body()?.let {
                return ResponseState.SuccessData(it)
            } ?: kotlin.run {
                return ResponseState.FailData
            }
        else
            return ResponseState.FailData

    }
}


sealed class ResponseState {
    data class SuccessData(val listOfImage: List<CatDetail>) : ResponseState()
    object FailData : ResponseState()
}