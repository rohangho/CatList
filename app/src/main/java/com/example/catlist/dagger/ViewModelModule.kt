package com.example.catlist.dagger

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.catlist.dagger.common.ViewModelFactory
import com.example.catlist.dagger.common.ViewModelKey
import com.example.catlist.ui.viewModel.CatViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(viewmodelFactory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(CatViewModel::class)
    internal abstract fun mainViewModel(viewModel: CatViewModel): ViewModel

}







