package br.com.matthaus.tinktest.repository

import br.com.matthaus.tinktest.network.DogAPI
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DogRepository {

    private lateinit var dogAPIClient : DogAPI

    init {
        dogAPIClient = Retrofit
            .Builder()
            .baseUrl("https://dog.ceo/api/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(DogAPI::class.java)
    }

    suspend fun getRandomDogs() = dogAPIClient.getRandomDogs(50)

}