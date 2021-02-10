package br.com.matthaus.tinktest.di

import br.com.matthaus.tinktest.repository.DogRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { DogRepository(get()) }
}