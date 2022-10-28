package com.example.catlist.domain.model

import com.google.gson.annotations.SerializedName

data class CatDetail(
    @SerializedName("url")
    val url: String? = null
)