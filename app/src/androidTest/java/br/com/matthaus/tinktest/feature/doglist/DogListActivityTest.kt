package br.com.matthaus.tinktest.feature.doglist

import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import br.com.matthaus.tinktest.R
import br.com.matthaus.tinktest.repository.DogRepository
import br.com.matthaus.tinktest.util.RecyclerViewItemCountAssertion
import io.mockk.coEvery
import io.mockk.mockk
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

@RunWith(AndroidJUnit4::class)
class DogListActivityTest {

    private val dogRepository = mockk<DogRepository>()
    private val dogListViewModel = DogListViewModel(dogRepository)

    @Before
    fun setUp() {
        loadKoinModules(module {
            single { dogListViewModel }
        })
    }

    @Test
    fun isImageCountRight_whenAPIResponseWithSuccess() {
        //Arrange
        coEvery { dogRepository.getRandomDogs() } returns listOf(
            "https://images.dog.ceo/breeds/springer-english/n02102040_6786.jpg",
            "https://images.dog.ceo/breeds/dhole/n02115913_1449.jpg",
            "https://images.dog.ceo/breeds/terrier-silky/n02097658_4402.jpg",
            "https://images.dog.ceo/breeds/spaniel-japanese/n02085782_845.jpg"
        )
        //Act
        launchActivity<DogListActivity>()
        //Assert
        onView(withId(R.id.dogs_list)).check(RecyclerViewItemCountAssertion(4))
    }

}