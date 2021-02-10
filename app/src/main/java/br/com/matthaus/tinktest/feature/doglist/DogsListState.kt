package br.com.matthaus.tinktest.feature.doglist

sealed class DogsListState {
    class Success(val imageUrls: List<String>) : DogsListState()
    object Loading : DogsListState()
    object Error : DogsListState()
}