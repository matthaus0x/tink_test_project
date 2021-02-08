package br.com.matthaus.tinktest.feature.doglist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import br.com.matthaus.tinktest.R

class MainActivity : AppCompatActivity() {

    private lateinit var dogListViewModel: DogListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dogListViewModel = ViewModelProvider(this).get(DogListViewModel::class.java)

        dogListViewModel
            .getRandomDogs()
            .observe(this) {
            }

    }
}