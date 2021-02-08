package br.com.matthaus.tinktest.feature.doglist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.matthaus.tinktest.network.model.RandomDogsResponse
import br.com.matthaus.tinktest.repository.DogRepository
import kotlinx.coroutines.launch

class DogListViewModel : ViewModel() {

    private var dogRepository: DogRepository

    private val _randomDogs : MutableLiveData<RandomDogsResponse> = MutableLiveData()

    init {
        dogRepository = DogRepository()
    }

    fun getRandomDogs() : LiveData<RandomDogsResponse> {
        viewModelScope.launch {
            val randomDogs = dogRepository.getRandomDogs()
            _randomDogs.postValue(randomDogs)
        }
        return _randomDogs
    }

}