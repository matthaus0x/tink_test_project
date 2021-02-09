package br.com.matthaus.tinktest.di

import br.com.matthaus.tinktest.network.DogAPI
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module{

    single(named("API_BASE_URL")) { "https://dog.ceo/api/" }

    single<Gson> { GsonBuilder().create() }

    single<Converter.Factory> { GsonConverterFactory.create(get()) }

    single<DogAPI> {
        Retrofit
            .Builder()
            .baseUrl(get<String>(named("API_BASE_URL")))
            .addConverterFactory(get())
            .build()
            .create(DogAPI::class.java)
    }
}

