package br.com.matthaus.tinktest.feature.doglist

import androidx.lifecycle.MutableLiveData
import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import br.com.matthaus.tinktest.R
import br.com.matthaus.tinktest.util.RecyclerViewItemCountAssertion
import io.mockk.every
import io.mockk.mockk
import org.hamcrest.CoreMatchers.not
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.dsl.module

@RunWith(AndroidJUnit4::class)
class DogListActivityTest {

    private val dogViewModel = mockk<DogListViewModel>(relaxed = true)

    private val mockModule = module {
        single { dogViewModel }
    }

    @Before
    fun setUp() {
        loadKoinModules(mockModule)
    }

    @Test
    fun isImageCountRight_whenSuccessState() {
        //Arrange
        val states = MutableLiveData<DogsListState>()
        every { dogViewModel.dogsListState } answers { states }
        every { dogViewModel.getRandomDogs() } returns Unit
        states.postValue(
            DogsListState.Success(
                listOf(
                    "https://images.dog.ceo/breeds/springer-english/n02102040_6786.jpg",
                    "https://images.dog.ceo/breeds/dhole/n02115913_1449.jpg",
                    "https://images.dog.ceo/breeds/terrier-silky/n02097658_4402.jpg",
                    "https://images.dog.ceo/breeds/spaniel-japanese/n02085782_845.jpg"
                )
            )
        )
        //Act
        launchActivity<DogListActivity>()
        //Assert
        onView(withId(R.id.dogs_list)).check(RecyclerViewItemCountAssertion(4))
    }

    @Test
    fun isImageListShown_whenSuccessState() {
        //Arrange
        val states = MutableLiveData<DogsListState>()
        every { dogViewModel.dogsListState } answers { states }
        every { dogViewModel.getRandomDogs() } returns Unit
        states.postValue(
            DogsListState.Success(
                listOf(
                    "https://images.dog.ceo/breeds/springer-english/n02102040_6786.jpg",
                    "https://images.dog.ceo/breeds/dhole/n02115913_1449.jpg",
                    "https://images.dog.ceo/breeds/terrier-silky/n02097658_4402.jpg",
                    "https://images.dog.ceo/breeds/spaniel-japanese/n02085782_845.jpg"
                )
            )
        )
        //Act
        launchActivity<DogListActivity>()
        //Assert
        onView(withId(R.id.dogs_list)).check(matches(isDisplayed()))
    }

    @Test
    fun isLoadingHide_whenSuccessState() {
        //Arrange
        val states = MutableLiveData<DogsListState>()
        every { dogViewModel.dogsListState } answers { states }
        every { dogViewModel.getRandomDogs() } returns Unit
        states.postValue(
            DogsListState.Success(
                listOf(
                    "https://images.dog.ceo/breeds/springer-english/n02102040_6786.jpg",
                    "https://images.dog.ceo/breeds/dhole/n02115913_1449.jpg",
                    "https://images.dog.ceo/breeds/terrier-silky/n02097658_4402.jpg",
                    "https://images.dog.ceo/breeds/spaniel-japanese/n02085782_845.jpg"
                )
            )
        )
        //Act
        launchActivity<DogListActivity>()
        //Assert
        onView(withId(R.id.loading)).check(matches(not(isDisplayed())))
    }

    @Test
    fun isLoadingShown_whenLoadingState() {
        //Arrange
        val states = MutableLiveData<DogsListState>()
        every { dogViewModel.dogsListState } answers { states }
        every { dogViewModel.getRandomDogs() } returns Unit
        states.postValue(DogsListState.Loading)
        //Act
        launchActivity<DogListActivity>()
        //Assert
        onView(withId(R.id.loading)).check(matches(isDisplayed()))
    }

    @Test
    fun isErrorShown_whenErrorState() {
        //Arrange
        val states = MutableLiveData<DogsListState>()
        every { dogViewModel.dogsListState } answers { states }
        every { dogViewModel.getRandomDogs() } returns Unit
        states.postValue(DogsListState.Error)
        //Act
        launchActivity<DogListActivity>()
        //Assert
        onView(withId(com.google.android.material.R.id.snackbar_text))
            .check(matches(withText("Error fetching dogs")))
    }

    @After
    fun tearDown() {
        unloadKoinModules(mockModule)
    }

}