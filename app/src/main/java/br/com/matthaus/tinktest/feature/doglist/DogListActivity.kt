package br.com.matthaus.tinktest.feature.doglist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.matthaus.tinktest.R

class DogListActivity : AppCompatActivity() {

    private lateinit var dogListViewModel: DogListViewModel
    private lateinit var dogList : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dog_list)

        dogList = findViewById(R.id.dogs_list)

        dogListViewModel = ViewModelProvider(this).get(DogListViewModel::class.java)

        dogListViewModel
            .getRandomDogs()
            .observe(this) {
                DogListAdapter(it.message)
                    .run {
                        dogList.apply {
                            layoutManager = GridLayoutManager(this@DogListActivity, 2)
                            adapter = this@run
                        }
                    }
            }

    }
}