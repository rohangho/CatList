package com.example.catlist.utils

abstract class Visitable {
    abstract fun getType(typeFactory: AdapterTypeFactory): Int
}