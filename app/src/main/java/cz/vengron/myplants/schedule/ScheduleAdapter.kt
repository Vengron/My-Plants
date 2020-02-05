package cz.vengron.myplants.schedule

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import cz.vengron.myplants.database.Plant
import cz.vengron.myplants.databinding.ListItemWateringBinding


class ScheduleAdapter(private val clickListener: ScheduleListener) : ListAdapter<Plant,
        ScheduleAdapter.ViewHolder>(ScheduleDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)

        holder.bind(clickListener, item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(val binding: ListItemWateringBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(clickListener: ScheduleListener, item: Plant) {
            binding.plant = item
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemWateringBinding.inflate(layoutInflater, parent, false)

                return ViewHolder(binding)
            }
        }
    }
}

class ScheduleDiffCallback : DiffUtil.ItemCallback<Plant>() {
    override fun areItemsTheSame(oldItem: Plant, newItem: Plant): Boolean {
        return oldItem.plantId == newItem.plantId
    }

    override fun areContentsTheSame(oldItem: Plant, newItem: Plant): Boolean {
        return oldItem == newItem
    }
}

class ScheduleListener(val clickListener: (plantId: Long) -> Unit) {
    fun onClick(night: Plant) = clickListener(night.plantId)
}