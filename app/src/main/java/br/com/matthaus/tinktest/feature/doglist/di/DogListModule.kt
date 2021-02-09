package br.com.matthaus.tinktest.feature.doglist.di

import br.com.matthaus.tinktest.feature.doglist.DogListViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val dogListModule = module {
    viewModel { DogListViewModel(get()) }
}