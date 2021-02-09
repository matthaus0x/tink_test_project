package br.com.matthaus.tinktest.feature.doglist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.matthaus.tinktest.R
import org.koin.android.viewmodel.ext.android.viewModel

class DogListActivity : AppCompatActivity() {

    private val dogListViewModel: DogListViewModel by viewModel()
    private lateinit var dogList : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dog_list)

        bindViews()
        fetchRandomDogs()
    }

    private fun bindViews() {
        dogList = findViewById(R.id.dogs_list)
    }

    private fun fetchRandomDogs() {
        dogListViewModel
                .getRandomDogs()
                .observe(this) {
                    DogListAdapter(it)
                            .run {
                                dogList.apply {
                                    layoutManager = GridLayoutManager(this@DogListActivity, 2)
                                    adapter = this@run
                                }
                            }
                }
    }

}