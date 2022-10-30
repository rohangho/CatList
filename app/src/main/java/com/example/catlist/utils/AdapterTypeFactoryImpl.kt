package com.example.catlist.utils

import android.view.View
import com.example.catlist.R
import com.example.catlist.domain.model.CatDetail
import com.example.catlist.ui.viewholder.CatIndividualView

class AdapterTypeFactoryImpl : AdapterTypeFactory {
    override fun type(catDetail: CatDetail): Int {
        return R.layout.cat_individual
    }

    override fun holder(type: Int, view: View): BaseViewHolder<*> {
        return when (type) {
            R.layout.cat_individual -> CatIndividualView(view)
            else -> throw NullPointerException()
        }
    }


}