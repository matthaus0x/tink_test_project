package br.com.matthaus.tinktest.repository

import br.com.matthaus.tinktest.network.DogAPI
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val RANDOM_DOGS_QUANTITY_PER_FETCH = 50

class DogRepository constructor(private val dogAPI : DogAPI) {

    suspend fun getRandomDogs() = dogAPI.getRandomDogs(RANDOM_DOGS_QUANTITY_PER_FETCH).message

}