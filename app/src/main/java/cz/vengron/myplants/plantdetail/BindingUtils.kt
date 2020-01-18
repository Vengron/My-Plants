package cz.vengron.myplants.plantdetail

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import cz.vengron.myplants.R
import cz.vengron.myplants.database.Plant
import java.text.SimpleDateFormat
import java.util.*

@BindingAdapter("plantImage")
fun ImageView.setPlantImage(plant: Plant?) {
    plant?.let {
        setImageResource(
            R.drawable.plant_test
        )
    }
}

@BindingAdapter("formattedWateringTime")
fun TextView.formattedWateringTime(plant: Plant?) {
    plant?.let {
        text = SimpleDateFormat("EEEE HH:mm", Locale.getDefault())
            .format(plant.timeForWatering)
    }
}