package com.example.catlist.utils

interface ViewTypeFactory {
    fun viewType(typeFactory: AdapterTypeFactory): Int

}
