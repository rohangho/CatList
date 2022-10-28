package com.example.catlist

import android.app.Application
import com.example.catlist.dagger.DaggerApplicationComponent

class MyApplication : Application() {

    val appComponent = DaggerApplicationComponent.create()
}