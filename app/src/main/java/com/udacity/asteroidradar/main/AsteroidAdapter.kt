package com.udacity.asteroidradar.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.udacity.asteroidradar.model.Asteroid
import com.udacity.asteroidradar.databinding.AsteroidItemBinding

class AsteroidAdapter(private val onClickListener: OnClickListener) :
    ListAdapter<Asteroid, AsteroidAdapter.AsteroidViewHolder>(DiffCallback) {

    class AsteroidViewHolder(private var binding: AsteroidItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun updateUI(asteriod: Asteroid?) {
//            binding.asteroidDate.text = asteriod?.closeApproachData?.get(0)?.closeApproachDate
//            binding.asteroidText.text = asteriod?.name
//            if(asteriod?.isPotentiallyHazardous == true){
//                binding.imageView.setImageResource(R.drawable.ic_status_potentially_hazardous)
//
//            }else if(asteriod?.isPotentiallyHazardous == false){
//                binding.imageView.setImageResource(R.drawable.ic_status_normal)
//            }
            binding.asteroid = asteriod
            binding.executePendingBindings()
        }

    }

    companion object DiffCallback : DiffUtil.ItemCallback<Asteroid>() {
        override fun areItemsTheSame(oldItem: Asteroid, newItem: Asteroid): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Asteroid, newItem: Asteroid): Boolean {
            return oldItem.id == newItem.id
        }

    }

    class OnClickListener(val clickListener: (asteroid: Asteroid) -> Unit) {
        fun onClick(asteroid: Asteroid) = clickListener(asteroid)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AsteroidViewHolder {
        return AsteroidViewHolder(AsteroidItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: AsteroidViewHolder, position: Int) {
        val asteriod = getItem(position)
        holder.updateUI(asteriod)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(asteriod)
        }
    }
}

