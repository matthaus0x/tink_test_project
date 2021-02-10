package br.com.matthaus.tinktest.feature.doglist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import br.com.matthaus.tinktest.R
import com.bumptech.glide.Glide

class DogListAdapter() :
    RecyclerView.Adapter<DogListAdapter.DogListViewHolder>() {

    var onDogClick: (String) -> Unit = {}

    private var dogList: List<String> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogListViewHolder {
        return LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_dog_list, parent, false)
            .let {
                DogListViewHolder(it)
            }
    }

    override fun onBindViewHolder(holder: DogListViewHolder, position: Int) {
        holder.bind(dogList[position])
        holder.onImageClick = onDogClick
    }

    override fun getItemCount() = dogList.size

    fun setDogList(dogList : List<String>) {
        this.dogList = dogList
        notifyDataSetChanged()
    }

    class DogListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val dogImage: ImageView = itemView.findViewById(R.id.dog_image)

        var onImageClick: (String) -> Unit = {}

        fun bind(imageUrl: String) {
            Glide
                .with(dogImage)
                .load(imageUrl)
                .centerCrop()
                .into(dogImage)

            dogImage.setOnClickListener {
                onImageClick(imageUrl)
            }
        }

    }

}