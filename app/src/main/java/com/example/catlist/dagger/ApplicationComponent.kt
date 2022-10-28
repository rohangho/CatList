package com.example.catlist.dagger

import com.example.catlist.ui.activity.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, ViewModelModule::class])
interface ApplicationComponent {
    fun inject(mainActivity: MainActivity)

}