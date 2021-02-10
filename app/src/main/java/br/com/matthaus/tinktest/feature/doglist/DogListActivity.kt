package br.com.matthaus.tinktest.feature.doglist

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.matthaus.tinktest.R
import br.com.matthaus.tinktest.feature.dogdetail.DogDetailActivity
import br.com.matthaus.tinktest.feature.dogdetail.EXTRA_DOG_IMAGE_URL
import com.google.android.material.snackbar.Snackbar
import org.koin.android.viewmodel.ext.android.viewModel

private const val DOG_LIST_COLUMN_QUANTITY = 2

class DogListActivity : AppCompatActivity() {

    private val dogListViewModel: DogListViewModel by viewModel()

    private lateinit var rvDogsList: RecyclerView
    private lateinit var pbLoading: ProgressBar
    private lateinit var clDogsListScreen: ConstraintLayout

    val dogListAdapter = DogListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dog_list)

        bindViews()
        setupViews()
        setObservers()
        setListeners()
        fetchRandomDogs()
    }

    private fun bindViews() {
        rvDogsList = findViewById(R.id.dogs_list)
        pbLoading = findViewById(R.id.loading)
        clDogsListScreen = findViewById(R.id.dogs_list_screen)
    }

    fun setupViews() {
        rvDogsList.apply {
            layoutManager = GridLayoutManager(this@DogListActivity, DOG_LIST_COLUMN_QUANTITY)
            adapter = dogListAdapter
        }
    }

    private fun setObservers() {
        dogListViewModel
            .dogsListState
            .observe(this) {
                when (it) {
                    is DogsListState.Success -> updateDogsList(it.imageUrls)
                    is DogsListState.Loading -> showLoading()
                    is DogsListState.Error -> showErrorAndRetry()
                }
            }
    }

    private fun setListeners() {
        dogListAdapter.onDogClick = {
            Intent(this, DogDetailActivity::class.java)
                .apply {
                    putExtra(EXTRA_DOG_IMAGE_URL, it)
                }
                .run {
                    startActivity(this)
                }
        }
    }

    private fun updateDogsList(dogsList: List<String>) {
        dogListAdapter.setDogList(dogsList)
        hideLoading()
    }

    private fun fetchRandomDogs() {
        dogListViewModel
            .getRandomDogs()
    }

    private fun showErrorAndRetry() {
        hideLoading()
        Snackbar
            .make(clDogsListScreen, R.string.error_fetching_dogs, Snackbar.LENGTH_INDEFINITE)
            .setAction(R.string.retry) {
                fetchRandomDogs()
            }
            .show()
    }

    private fun showLoading() {
        pbLoading.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        pbLoading.visibility = View.GONE
    }
}