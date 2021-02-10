package br.com.matthaus.tinktest.feature.doglist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.matthaus.tinktest.repository.DogRepository
import kotlinx.coroutines.launch

class DogListViewModel(private val dogRepository: DogRepository) : ViewModel() {

    private val _dogsListState: MutableLiveData<DogsListState> = MutableLiveData()

    val dogsListState: LiveData<DogsListState> = _dogsListState

    fun getRandomDogs() {
        _dogsListState.postValue(DogsListState.Loading)
        viewModelScope.launch {
            try {
                val randomDogs = dogRepository.getRandomDogs()
                _dogsListState.postValue(DogsListState.Success(randomDogs))
            } catch (ex: Exception) {
                _dogsListState.postValue(DogsListState.Error)
            }
        }
    }

}