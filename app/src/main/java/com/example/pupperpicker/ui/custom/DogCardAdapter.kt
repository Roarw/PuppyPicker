package com.example.pupperpicker.ui.custom

import android.graphics.Bitmap
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope

class DogCardAdapter(private val viewModelScope: CoroutineScope?, private val dogs: List<Pair<Bitmap?, String>>) : RecyclerView.Adapter<DogCardAdapter.DogCardViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogCardViewHolder {
        return DogCardViewHolder(DogCard(parent.context))
    }

    override fun onBindViewHolder(holder: DogCardViewHolder, position: Int) {
        val image = dogs[position].first
        if (image != null) {
            holder._dogCard.setImage(image)
        }
        holder._dogCard.setURL(dogs[position].second)
        if (viewModelScope != null) {
            holder._dogCard.setupButton(viewModelScope)
        }
    }

    override fun getItemCount(): Int {
        return dogs.size
    }

    class DogCardViewHolder(dogCard: DogCard) : RecyclerView.ViewHolder(dogCard) {
        val _dogCard: DogCard = dogCard
    }
}