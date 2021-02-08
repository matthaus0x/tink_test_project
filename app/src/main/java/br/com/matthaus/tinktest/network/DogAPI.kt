package br.com.matthaus.tinktest.network

import br.com.matthaus.tinktest.network.model.RandomDogsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface DogAPI {
    @GET("breeds/image/random/{quantity}")
    suspend fun getRandomDogs(@Path("quantity") quantity: Int) : RandomDogsResponse
}