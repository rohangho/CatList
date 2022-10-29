package com.example.catlist.ui.adapter

import com.example.catlist.domain.model.CatDetail
import com.example.catlist.utils.AdapterTypeFactory
import com.example.catlist.utils.ViewTypeFactory

class CatViewTyprFactoryImpl(val carDetail: CatDetail):ViewTypeFactory {
    override fun viewType(typeFactory: AdapterTypeFactory): Int {
     return  typeFactory.type(catDetail = carDetail)
    }
}