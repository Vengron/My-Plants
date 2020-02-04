package cz.vengron.myplants.collection

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import cz.vengron.myplants.R
import cz.vengron.myplants.database.Plant
import cz.vengron.myplants.databinding.ListItemPlantBinding
import cz.vengron.myplants.setPlantImage


class PlantAdapter(private val clickListener: PlantListener) : ListAdapter<Plant,
        PlantAdapter.ViewHolder>(PlantDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)

        holder.bind(clickListener, item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(val binding: ListItemPlantBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(clickListener: PlantListener, item: Plant) {
            binding.plant = item
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemPlantBinding.inflate(layoutInflater, parent, false)

                return ViewHolder(binding)
            }
        }
    }
}

class PlantDiffCallback : DiffUtil.ItemCallback<Plant>() {
    override fun areItemsTheSame(oldItem: Plant, newItem: Plant): Boolean {
        return oldItem.plantId == newItem.plantId
    }

    override fun areContentsTheSame(oldItem: Plant, newItem: Plant): Boolean {
        return oldItem == newItem
    }
}

class PlantListener(val clickListener: (plantId: Long) -> Unit) {
    fun onClick(night: Plant) = clickListener(night.plantId)
}