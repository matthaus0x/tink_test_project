package br.com.matthaus.tinktest.feature.dogdetail

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import br.com.matthaus.tinktest.R
import com.bumptech.glide.Glide

const val EXTRA_DOG_IMAGE_URL = "EXTRA_DOG_IMAGE_URL"

class DogDetailActivity : AppCompatActivity() {

    private lateinit var dogImage: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dog_detail)

        bindViews()

        intent.getStringExtra(EXTRA_DOG_IMAGE_URL)?.let {
            setupViews(it)
        }

    }

    private fun bindViews() {
        dogImage = findViewById(R.id.dog_image)
    }

    private fun setupViews(dogImageUrl: String) {
        Glide
            .with(this)
            .load(dogImageUrl)
            .into(dogImage)
    }

}