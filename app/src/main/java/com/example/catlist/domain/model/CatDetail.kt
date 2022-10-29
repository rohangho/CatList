package com.example.catlist.domain.model

import com.example.catlist.utils.AdapterTypeFactory
import com.example.catlist.utils.Visitable
import com.google.gson.annotations.SerializedName

data class CatDetail(
    @SerializedName("url")
    val url: String? = null
): Visitable() {
    override fun getType(typeFactory: AdapterTypeFactory): Int {
        return typeFactory.type(this)
    }

}
