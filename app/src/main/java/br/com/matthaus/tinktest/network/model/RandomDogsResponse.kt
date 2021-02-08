package br.com.matthaus.tinktest.network.model

import com.google.gson.annotations.SerializedName

data class RandomDogsResponse(@SerializedName("message") val message: List<String>)
