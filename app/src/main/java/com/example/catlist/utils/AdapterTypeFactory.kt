package com.example.catlist.utils

import android.view.View
import com.example.catlist.domain.model.CatDetail

interface AdapterTypeFactory {
    fun type(catDetail: CatDetail) :Int
    fun holder(type: Int, view: View): BaseViewHolder<*>
}
