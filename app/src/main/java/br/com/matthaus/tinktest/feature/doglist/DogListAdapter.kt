package br.com.matthaus.tinktest.feature.doglist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import br.com.matthaus.tinktest.R
import com.bumptech.glide.Glide

class DogListAdapter(private val images : List<String>) : RecyclerView.Adapter<DogListAdapter.DogListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogListViewHolder {
        return LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_dog_list, parent, false)
            .let {
               DogListViewHolder(it)
            }
    }

    override fun onBindViewHolder(holder: DogListViewHolder, position: Int) {
        holder.bind(images[position])
    }

    override fun getItemCount() = images.size

    class DogListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val dogImage : ImageView = itemView.findViewById(R.id.dog_image)

        fun bind(imageUrl: String) {
            Glide
                .with(dogImage)
                .load(imageUrl)
                .centerCrop()
                .into(dogImage)
        }

    }

}