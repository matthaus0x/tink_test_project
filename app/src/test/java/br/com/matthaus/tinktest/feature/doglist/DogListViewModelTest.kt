package br.com.matthaus.tinktest.feature.doglist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import br.com.matthaus.tinktest.repository.DogRepository
import io.mockk.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class DogListViewModelTest {

    @get:Rule
    var instantExecutorRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        Dispatchers.setMain(Dispatchers.IO)
    }

    @Test
    fun `given the API returned with success, than the dog list state should be loading than success`() {
        //Arrange
        val mockDogImages = listOf(
            "https://images.dog.ceo/breeds/springer-english/n02102040_6786.jpg",
            "https://images.dog.ceo/breeds/dhole/n02115913_1449.jpg",
            "https://images.dog.ceo/breeds/terrier-silky/n02097658_4402.jpg",
            "https://images.dog.ceo/breeds/spaniel-japanese/n02085782_845.jpg"
        )

        val dogRepository = mockk<DogRepository>()
        coEvery { dogRepository.getRandomDogs() } returns mockDogImages

        val dogListViewModel = DogListViewModel(dogRepository)
        val mockObserver = spyk<Observer<DogsListState>>()
        dogListViewModel.dogsListState.observeForever(mockObserver)

        val dogImageSuccessSlot = slot<DogsListState.Success>()

        //Act
        dogListViewModel.getRandomDogs()


        //Assert
        verifyOrder {
            mockObserver.onChanged(DogsListState.Loading)
            mockObserver.onChanged(capture(dogImageSuccessSlot))
        }

        assertEquals(dogImageSuccessSlot.captured.imageUrls, mockDogImages)
    }

    @Test
    fun `given the API return with error, than the dog list state should be loading than error`() {
        //Arrange
        val dogRepository = mockk<DogRepository>()
        coEvery { dogRepository.getRandomDogs() } throws Exception()

        val dogListViewModel = DogListViewModel(dogRepository)
        val mockObserver = spyk<Observer<DogsListState>>()
        dogListViewModel.dogsListState.observeForever(mockObserver)

        //Act
        dogListViewModel.getRandomDogs()


        //Assert
        verifyOrder {
            mockObserver.onChanged(DogsListState.Loading)
            mockObserver.onChanged(DogsListState.Error)
        }
    }

}